package org.sid.poccadorimaxon.queries.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.mapstruct.factory.Mappers;
import org.sid.poccadorimaxon.commonapi.dtos.ProductResponseDTO;
import org.sid.poccadorimaxon.commonapi.events.ProductCreatedEvent;
import org.sid.poccadorimaxon.commonapi.queries.GetAllProductsQuery;
import org.sid.poccadorimaxon.commonapi.queries.GetProductByIdQuery;
import org.sid.poccadorimaxon.queries.entities.Product;
import org.sid.poccadorimaxon.queries.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceHandler {
    private ProductRepository productRepository;
    @EventHandler
    public  void on(ProductCreatedEvent event){
        log.info("*******************");
        log.info("ProductCreatedEvent received");
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setDescription(event.getDescription());
        product.setPrice(event.getPrice());
        productRepository.save(product);
    }

    @QueryHandler
    public List<ProductResponseDTO> on(GetAllProductsQuery query){
        List<Product> productList= productRepository.findAll();
        return productList.stream().map(pm->ProductMapper.INSTANCE.productToProductResponseDTO(pm)).collect(Collectors.toList());
    }
    @QueryHandler
    public ProductResponseDTO on(GetProductByIdQuery query){
        Product product= productRepository.findById(query.getId()).get();
        ProductResponseDTO productDto =  ProductMapper.INSTANCE.productToProductResponseDTO(product);
        return productDto;
    }
}
