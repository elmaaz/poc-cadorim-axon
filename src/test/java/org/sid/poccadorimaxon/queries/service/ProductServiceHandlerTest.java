package org.sid.poccadorimaxon.queries.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sid.poccadorimaxon.commonapi.dtos.ProductResponseDTO;
import org.sid.poccadorimaxon.commonapi.queries.GetAllProductsQuery;
import org.sid.poccadorimaxon.commonapi.queries.GetProductByIdQuery;
import org.sid.poccadorimaxon.queries.entities.Product;
import org.sid.poccadorimaxon.queries.repositories.ProductRepository;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceHandler productServiceHandler;

    private GetAllProductsQuery getAllProductsQuery;
    private GetProductByIdQuery getProductByIdQuery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllProducts() {

        Product product1 = new Product("12345", "Prod4","Des4",400);
        Product product2 = new Product("16433", "Prod5","Des5",500);

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponseDTO> result = productServiceHandler.on(getAllProductsQuery);

        assertEquals(2, result.size());
    }


    @Test
    public void testGetProductById() {
        Product product = new Product("12345", "Prod6","Des6",600);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        getProductByIdQuery = new GetProductByIdQuery("12345");
        ProductResponseDTO result = productServiceHandler.on(getProductByIdQuery);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getPrice(), result.getPrice());
    }

    @Test
    public void testGetProductByIdNotFound() {
        String  productId = "12345";
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        getProductByIdQuery = new GetProductByIdQuery(productId);
        assertThrows(NoSuchElementException.class, () -> productServiceHandler.on(getProductByIdQuery));
    }

}