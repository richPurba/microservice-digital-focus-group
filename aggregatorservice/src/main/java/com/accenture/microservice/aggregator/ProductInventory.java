package com.accenture.microservice.aggregator;

import java.io.IOException;

public interface ProductInventory {
    public int getInventory()throws IOException;
}
