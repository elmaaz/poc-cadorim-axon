package org.sid.poccadorimaxon.queries.service;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.poccadorimaxon.commonapi.dtos.ProductResponseDTO;
import org.sid.poccadorimaxon.queries.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductResponseDTO productToProductResponseDTO(Product product);
}
