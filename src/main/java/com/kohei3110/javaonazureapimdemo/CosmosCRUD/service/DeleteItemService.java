package com.kohei3110.javaonazureapimdemo.CosmosCRUD.service;

import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.DeleteItemRepository;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;

public class DeleteItemService {
    
    private DeleteItemRepository deleteItemRepository;

    public DeleteItemService(DeleteItemRepository deleteItemRepository) {
        this.deleteItemRepository = deleteItemRepository;
    }

    public HttpResponseMessage requestCosmosDB(
        String id,
        HttpRequestMessage<Optional<String>> request
    ) {
        return this.deleteItemRepository.requestCosmosDB(id, request);
    }
}