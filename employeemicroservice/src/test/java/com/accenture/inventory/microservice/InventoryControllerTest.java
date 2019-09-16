package com.accenture.inventory.microservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryControllerTest {
    @Test
    public void testGetProductInventories(){
        InventoryController controller = new InventoryController();
        int numberOfInventoryController = controller.getProductInventories();

        assertEquals(5, numberOfInventoryController);
    }
}
