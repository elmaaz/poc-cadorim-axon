package org.sid.poccadorimaxon.commonapi.commands;

import lombok.Getter;

public class CreateProductCommand extends BaseCommand<String>{
    @Getter private  String name;
    @Getter private  String description;
    @Getter private double price;

    public CreateProductCommand(String id, String name, String description, double price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
