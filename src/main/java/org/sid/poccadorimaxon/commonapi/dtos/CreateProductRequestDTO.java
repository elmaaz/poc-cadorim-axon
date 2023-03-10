package org.sid.poccadorimaxon.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDTO {
    private  String name;
    private  String description;
    private double price;
}
