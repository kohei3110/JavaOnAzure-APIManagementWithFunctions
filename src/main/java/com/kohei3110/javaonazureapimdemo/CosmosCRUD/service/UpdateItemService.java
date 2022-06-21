package com.kohei3110.javaonazureapimdemo.CosmosCRUD.service;

import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.UpdateItemRepository;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.OutputBinding;

public class UpdateItemService {
    
    private UpdateItemRepository updateItemRepository;

    public UpdateItemService(UpdateItemRepository updateItemRepository) {
        this.updateItemRepository = updateItemRepository;
    }

    public HttpResponseMessage requestCosmosDB(
        HttpRequestMessage<Optional<String>> request,
        OutputBinding<String> outputItem)
    {
        return this.updateItemRepository.requestCosmosDB(request, outputItem);
    }
}
