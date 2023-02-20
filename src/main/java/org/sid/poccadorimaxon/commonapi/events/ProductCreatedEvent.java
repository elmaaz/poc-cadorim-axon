package org.sid.poccadorimaxon.commonapi.events;

import lombok.Getter;

public class ProductCreatedEvent extends BaseEvent<String >{
    @Getter private  String name;
    @Getter private  String description;
    @Getter private double price;

    public ProductCreatedEvent(String id, String name, String description, double price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
