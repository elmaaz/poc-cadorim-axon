package org.sid.poccadorimaxon.queries.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.sid.poccadorimaxon.commonapi.dtos.ProductResponseDTO;
import org.sid.poccadorimaxon.commonapi.queries.GetAllProductsQuery;
import org.sid.poccadorimaxon.commonapi.queries.GetProductByIdQuery;
import org.sid.poccadorimaxon.queries.entities.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queries/products")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@AllArgsConstructor
@Slf4j
public class ProductQueryController {
    private QueryGateway queryGateway;
    @GetMapping("/allProducts")
    public List<ProductResponseDTO> productList(){
        List<ProductResponseDTO> response =
                queryGateway.
                        query(
                                new GetAllProductsQuery(),
                                ResponseTypes.multipleInstancesOf((ProductResponseDTO.class))
                        ).join();
        return response;
    }

    @GetMapping("/byId/{id}")
    public ProductResponseDTO productList(@PathVariable String id){
        ProductResponseDTO response =
                queryGateway.
                        query(
                                new GetProductByIdQuery(id),
                                ResponseTypes.instanceOf((ProductResponseDTO.class))
                        ).join();
        return response;
    }
}
