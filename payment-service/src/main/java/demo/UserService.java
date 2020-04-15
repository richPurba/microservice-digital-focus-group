package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @HystrixCommand()//Fast Fail Hope last page saves something
    @CacheEvict(value = "user", key = "#user.getId()")
    public User createUser(User user) {

        User result = null;

        if (!userRepository.exists(user.getId())) {
            result = this.userRepository.save(user);
        }

        return result;
    }

    @HystrixCommand()//pw
    @Cacheable(value = "user")
    public User getUser(String id) {
        return this.userRepository.findOne(id);
    }

    @HystrixCommand()//pw
    @CachePut(value = "user", key = "#id")
    public User updateUser(String id, User user) {

        User result = null;

        if (userRepository.exists(user.getId())) {
            result = this.userRepository.save(user);
        }

        return result;
    }


    @CacheEvict(value = "user", key = "#id")
    public boolean deleteUser(String id) {

        boolean deleted = false;

        if (userRepository.exists(id)) {
            this.userRepository.delete(id);
            deleted = true;
        }

        return deleted;
    }
}
