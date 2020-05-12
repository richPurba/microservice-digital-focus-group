package demo.api.v1;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import demo.account.Account;
import demo.account.AccountRepository;
import demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceV1 {

    private AccountRepository accountRepository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public AccountServiceV1(AccountRepository accountRepository,
                            @LoadBalanced OAuth2RestTemplate oAuth2RestTemplate) {
        this.accountRepository = accountRepository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

//    @HystrixCommand(
//            fallbackMethod = "getUserAccountsFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "fallback.enabled", value = "false"),
//                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "200"),
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//            },
//            threadPoolKey = "getUserAccountsThreadPool",
//            threadPoolProperties = {
//                    @HystrixProperty(name="coreSize",value="10"),
//                    @HystrixProperty(name="maxQueueSize", value="5")
//            })
    @HystrixCommand(
            groupKey = "AccountGroup",
            commandProperties={
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "200"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    public List<Account> getUserAccounts() {
        List<Account> account = null;
        User user = oAuth2RestTemplate.getForObject("http://user-service/uaa/v1/me", User.class);
        if (user != null) {
            account = accountRepository.findAccountsByUserId(user.getUsername());
        }

        // Mask credit card numbers
        if (account != null) {
            account.forEach(acct -> acct.getCreditCards()
                    .forEach(card ->
                            card.setNumber(card.getNumber()
                                    .replaceAll("([\\d]{4})(?!$)", "****-"))));
        }

        return account;
    }


}
