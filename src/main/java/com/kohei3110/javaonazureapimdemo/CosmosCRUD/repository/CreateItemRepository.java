package com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository;

import java.util.Optional;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;

public class CreateItemRepository {
    
    public HttpResponseMessage requestCosmosDB(
        HttpRequestMessage<Optional<String>> request, 
        OutputBinding<String> outputItem) 
    {
        outputItem.setValue(request.getBody().get());
        return request.createResponseBuilder(HttpStatus.CREATED)
            .header("Content-Type", "application/json")
            .body(request.getBody().get())
            .build();
    }
}
