package org.sid.poccadorimaxon.commands.aggregates;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.sid.poccadorimaxon.commonapi.commands.CreateProductCommand;
import org.sid.poccadorimaxon.commonapi.events.ProductCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {
    @AggregateIdentifier
    private  String productId;
    private  String name;
    private  String description;
    private double price;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        apply(new ProductCreatedEvent(
                createProductCommand.getId(),
                createProductCommand.getName(),
                createProductCommand.getDescription(),
                createProductCommand.getPrice()
        ));
    }

    @EventSourcingHandler
    public void onProductCreateEvent(ProductCreatedEvent event){
        this.productId=event.getId();
        this.name=event.getName();
        this.description=event.getDescription();
        this.price=event.getPrice();
    }
}
