package com.kohei3110.javaonazureapimdemo.CosmosCRUD.service;

import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.model.Item;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.GetItemRepository;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;

public class GetItemService {
    
    private GetItemRepository getItemRepository;

    public GetItemService(GetItemRepository getItemRepository) {
        this.getItemRepository = getItemRepository;
    }

    public HttpResponseMessage requestCosmosDB(
            String id, 
            HttpRequestMessage<Optional<String>> request, 
            Optional<Item> item
        ) 
        {
            return this.getItemRepository.requestCosmosDB(id, request, item);
        }
}