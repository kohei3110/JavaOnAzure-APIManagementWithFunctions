package com.kohei3110.javaonazureapimdemo.CosmosCRUD.service;

import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.CreateItemRepository;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.OutputBinding;

public class CreateItemService {
    
    private CreateItemRepository createItemRepository;

    public CreateItemService(CreateItemRepository createItemRepository) {
        this.createItemRepository = createItemRepository;
    }

    public HttpResponseMessage requestCosmosDB(
        HttpRequestMessage<Optional<String>> request, 
        OutputBinding<String> outputItem)
    {
        return this.createItemRepository.requestCosmosDB(request, outputItem);
    }
}
