package org.sid.poccadorimaxon.commands.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.aspectj.lang.annotation.Before;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sid.poccadorimaxon.commonapi.commands.CreateProductCommand;
import org.sid.poccadorimaxon.commonapi.dtos.CreateProductRequestDTO;
import org.sid.poccadorimaxon.commonapi.events.ProductCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.CompletableFuture;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.axonframework.common.Assert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@WebMvcTest(ProductCommandController.class)
@AutoConfigureMockMvc(addFilters  = false)
class ProductCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockBean
    private CommandGateway commandGateway;


    @Test
    public void createProductTest() throws Exception {
        CreateProductRequestDTO prodDTO = new CreateProductRequestDTO("prod8","Desc8",800);
        String requestBody = new ObjectMapper().writeValueAsString(prodDTO);

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent("12345","prod8","Desc8",800);
        CompletableFuture<Object> future = CompletableFuture.completedFuture(productCreatedEvent);
        when(commandGateway.send(any(CreateProductCommand.class))).thenReturn(future);;

        MvcResult result = mockMvc.perform(
                post("/commands/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();


    }

}