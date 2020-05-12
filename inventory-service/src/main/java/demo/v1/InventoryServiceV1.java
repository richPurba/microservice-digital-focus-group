package demo.v1;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import demo.inventory.Inventory;
import demo.inventory.InventoryRepository;
import demo.product.Product;
import demo.product.ProductRepository;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InventoryServiceV1 {
    private InventoryRepository inventoryRepository;
    private ProductRepository productRepository;
    private Session neo4jTemplate;

    @Autowired
    public InventoryServiceV1(InventoryRepository inventoryRepository,
                              ProductRepository productRepository, Session neo4jTemplate) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.neo4jTemplate = neo4jTemplate;
    }

    @HystrixCommand(fallbackMethod = "getProductFallback",groupKey = "InventoryGroup")
    public Product getProduct(String productId) {
        Product product;

        product = productRepository.getProductByProductId(productId);

        if (product != null) {
            Stream<Inventory> availableInventory = inventoryRepository.getAvailableInventoryForProduct(productId).stream();
            product.setInStock(availableInventory.findAny().isPresent());
        }
//        else {
//            /* PW: if product is not existing*/
//            throw new RuntimeException("Product not existing!");
//        }

        return product;
    }

    public Product getProductFallback(String productId) {
        /* PW: return a new fake product*/
        //return new Product("Product"+productId ,productId,"Connection failure, please try again",0.0d);
        return null;
    }

    @HystrixCommand(fallbackMethod = "getAvailableInventoryForProductIdsFallback",groupKey = "InventoryGroup")
    public List<Inventory> getAvailableInventoryForProductIds(String productIds) {
        List<Inventory> inventoryList;

        inventoryList = inventoryRepository.getAvailableInventoryForProductList(productIds.split(","));

        return neo4jTemplate.loadAll(inventoryList, 1)
                .stream().collect(Collectors.toList());
    }

    public List<Inventory> getAvailableInventoryForProductIdsFallback(String productIds) {
        return null;
    }
}
