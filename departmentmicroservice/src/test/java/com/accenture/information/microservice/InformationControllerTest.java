package com.accenture.information.microservice;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class InformationControllerTest {

    @Test
    public void shouldGetProductTitle(){
        InformationController informationController = new InformationController();

        String title = informationController.getProductTitle();

        assertEquals("The Product Title",title);
    }
}
