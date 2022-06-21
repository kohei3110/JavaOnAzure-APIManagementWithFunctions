package com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository;

import java.util.Optional;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.model.Item;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;

public class GetItemRepository {

    public HttpResponseMessage requestCosmosDB(
        String id,
        HttpRequestMessage<Optional<String>> request,
        Optional<Item> inputItem
    ) {
        if (inputItem.isPresent()) {
            return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(inputItem.get())
                .build();
        }
        return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Internal Server Error").build();
    }
}