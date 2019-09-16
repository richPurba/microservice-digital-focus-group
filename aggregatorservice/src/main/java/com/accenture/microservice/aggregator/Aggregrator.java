package com.accenture.microservice.aggregator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class Aggregrator {
    // Ask the container to 'put' the bean here
    @Resource
    private ProductInformation productInformation;

    @Resource
    private ProductInventory productInventory;

    @GetMapping("/product")
    public Product getProduct() throws IOException{
        Product product = new Product();
        try{
            product.setNumberInventory(productInventory.getInventory());
            product.setTitle(productInformation.getProductInformation());
        }catch (IOException e){
            throw new IOException("Failed to make a new Product because of "+e);
        }
        return product;

    }

}
