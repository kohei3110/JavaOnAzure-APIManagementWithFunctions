package com.kohei3110.javaonazureapimdemo;

import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.Factory;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.model.Item;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.CreateItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.DeleteItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.GetItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.UpdateItemService;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.CosmosDBInput;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class Controller {

    Factory factory = new Factory();

    @FunctionName("GetOrDeleteItem")
    public HttpResponseMessage getOrDeleteItem(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET, HttpMethod.DELETE},
            route = "Items/{id}",
            authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
        @BindingName("id") String id,
        @CosmosDBInput(
            name = "DBInput",
            databaseName = "Items",
            collectionName = "Items",
            id = "{id}",
            partitionKey = "{id}",
            connectionStringSetting = "ItemDatabaseConnectionString")
            Optional<Item> inputItem, 
        final ExecutionContext context) {

        if (request.getHttpMethod() == HttpMethod.GET) {
            if (inputItem.isPresent()) {
                GetItemService getItemService = factory.injectGetItemService();
                return getItemService.requestCosmosDB(id, request, inputItem);
            }
            if (StringUtils.isEmpty(id)) {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Invalid parameter")
                    .build();
            }
            return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(null)
                .build();
        }

        if (request.getHttpMethod() == HttpMethod.DELETE) {
            if (!StringUtils.isEmpty(id)) {
                DeleteItemService deleteItemService = factory.injectDeleteItemService();
                return deleteItemService.requestCosmosDB(id, request);
            } else {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Invalid parameter")
                    .build();
            }
        }

        return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("This HTTP Method is not supported on this application").build();
    }

    @FunctionName("PostOrPutItem")
    public HttpResponseMessage postOrPutItem(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.POST, HttpMethod.PUT},
            route = "Items/",
            authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
        @CosmosDBOutput(
            name = "DBOutput",
            databaseName = "Items",
            collectionName = "Items",
            connectionStringSetting = "ItemDatabaseConnectionString")
            OutputBinding<String> outputItem,
        final ExecutionContext context) {

        if (request.getHttpMethod() == HttpMethod.POST) {
            if (request.getBody().isPresent()) {
                CreateItemService createItemService = factory.injectCreateItemService();
                return createItemService.requestCosmosDB(request, outputItem);
            }
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("invalid parameter")
                .build();
        }

        if (request.getHttpMethod() == HttpMethod.PUT) {
            if (request.getBody().isPresent()) {
                UpdateItemService updateItemService = factory.injectUpdateItemService();
                return updateItemService.requestCosmosDB(request, outputItem);
            }
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("invalid parameter")
                .build();
        }

        return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
            .body("This HTTP Method is not supported on this application")
            .build();
    }
}
