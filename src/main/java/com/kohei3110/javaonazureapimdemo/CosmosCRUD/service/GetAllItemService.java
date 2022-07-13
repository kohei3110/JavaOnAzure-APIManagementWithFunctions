package com.kohei3110.javaonazureapimdemo.CosmosCRUD.service;

import java.util.List;
import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.model.Item;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.GetAllItemRepository;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;

public class GetAllItemService {
    
    private GetAllItemRepository getAllItemRepository;

    public GetAllItemService(GetAllItemRepository getAllItemRepository) {
        this.getAllItemRepository = getAllItemRepository;
    }

    public HttpResponseMessage requestCosmosDB(
            HttpRequestMessage<Optional<String>> request, 
            Optional<List<Item>> item
        ) 
        {
            return this.getAllItemRepository.requestCosmosDB(request, item);
        }
}
