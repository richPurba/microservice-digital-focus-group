package demo.api.v1;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import demo.catalog.Catalog;
import demo.catalog.CatalogInfo;
import demo.catalog.CatalogInfoRepository;
import demo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
public class CatalogServiceV1 {
    private CatalogInfoRepository catalogInfoRepository;
    private RestTemplate restTemplate;

    @Autowired
    public CatalogServiceV1(CatalogInfoRepository catalogInfoRepository,
                            @LoadBalanced RestTemplate restTemplate) {
        this.catalogInfoRepository = catalogInfoRepository;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getCatalogFallback",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
            },
            threadPoolKey = "getCatalogThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value="5"),
                    @HystrixProperty(name="maxQueueSize", value="5")
            })
    public Catalog getCatalog() {
        Catalog catalog;

        CatalogInfo activeCatalog = catalogInfoRepository.findCatalogByActive(true);

        catalog = restTemplate.getForObject(String.format("http://inventory-service/api/catalogs/search/findCatalogByCatalogNumber?catalogNumber=%s",
                activeCatalog.getCatalogId()), Catalog.class);

        ProductsResource products = restTemplate.getForObject(String.format("http://inventory-service/api/catalogs/%s/products",
                catalog.getId()), ProductsResource.class);

        catalog.setProducts(products.getContent().stream().collect(Collectors.toSet()));
        return catalog;
    }

    public Catalog getCatalogFallback() {
        /* fail slient*/
        return null;
    }

    @HystrixCommand(
            fallbackMethod = "getProductFallback",
            threadPoolKey = "getProductThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value="5"),
                    @HystrixProperty(name="maxQueueSize", value="5")
            }
    )
    public Product getProduct(String productId) {
        return restTemplate.getForObject(String.format("http://inventory-service/v1/products/%s",
                productId), Product.class);
    }

    public Product getProductFallback(String productId) {
        /* PW: return a new fake product*/
        Product fake = new Product("Product "+productId,productId,0.0d);
        fake.setDescription("You see this product due to error, please try again later.");
        return fake;
    }
}
