package org.sid.poccadorimaxon.commands.aggregates;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.sid.poccadorimaxon.commonapi.commands.CreateProductCommand;
import org.sid.poccadorimaxon.commonapi.events.ProductCreatedEvent;
import org.sid.poccadorimaxon.queries.entities.Product;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductAggregateTest {

    private FixtureConfiguration fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture(ProductAggregate.class);
    }

    @Test
    public void createProductTest() {

        Product prod = new Product(UUID.randomUUID().toString(), "Prod1","Des1",100);
        fixture.givenNoPriorActivity()
                .when(new CreateProductCommand(prod.getId(), prod.getName(), prod.getDescription(), prod.getPrice()))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new ProductCreatedEvent(prod.getId(), prod.getName(), prod.getDescription(), prod.getPrice()));
    }

}