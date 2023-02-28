package org.sid.poccadorimaxon.commonapi.commands;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductCommandTest {
    @Test
    public void testCreateProductCommandTest(){
        String id = UUID.randomUUID().toString();
        String name ="prod2";
        String description = "desc2";
        Double price = 200.0;

        CreateProductCommand createProductCommand = new CreateProductCommand(id,name,description,price);
        assertAll("Should return all entered values",
                ()-> assertEquals(id,createProductCommand.getId()),
                ()-> assertEquals(name,createProductCommand.getName()),
                ()-> assertEquals(description,createProductCommand.getDescription()),
                ()-> assertEquals(price,createProductCommand.getPrice())
                );
    }

}