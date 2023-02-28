package org.sid.poccadorimaxon.commonapi.events;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductCreatedEventTest {
    @Test
    public void testProductCreatedEventTest(){
        String id = UUID.randomUUID().toString();
        String name ="prod3";
        String description = "desc3";
        Double price = 300.0;

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(id,name,description,price);
        assertAll("Should return all entered values",
                ()-> assertEquals(id,productCreatedEvent.getId()),
                ()-> assertEquals(name,productCreatedEvent.getName()),
                ()-> assertEquals(description,productCreatedEvent.getDescription()),
                ()-> assertEquals(price,productCreatedEvent.getPrice())
        );
    }

}