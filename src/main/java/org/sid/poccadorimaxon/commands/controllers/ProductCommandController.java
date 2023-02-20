package org.sid.poccadorimaxon.commands.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.sid.poccadorimaxon.commonapi.commands.CreateProductCommand;
import org.sid.poccadorimaxon.commonapi.dtos.CreateProductRequestDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path="/commands/product")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@AllArgsConstructor
public class ProductCommandController {
    private CommandGateway commandGateway;

    @PostMapping(path="/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPrice()
        ));
        return response;
    }
}
