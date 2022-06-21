package com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosAsyncDatabase;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;

public class DeleteItemRepository {
    
    private CosmosAsyncClient cosmosAsyncClient;
    private CosmosAsyncDatabase cosmosAsyncDatabase;
    private CosmosAsyncContainer cosmosAsyncContainer;

    private static final String DATABASE_ID = "Items";
    private static final String CONTAINER_ID = "Items";

    Logger log = LoggerFactory.getLogger(DeleteItemRepository.class.getName());

    public DeleteItemRepository() {
        try {
            this.cosmosAsyncClient = new CosmosClientBuilder()
                .endpoint(System.getenv("COSMOSDB_ENDPOINT"))
                .key(System.getenv("COSMOSDB_KEY"))
                .contentResponseOnWriteEnabled(true)
                .buildAsyncClient();
            this.cosmosAsyncDatabase = this.cosmosAsyncClient.getDatabase(DATABASE_ID);
            this.cosmosAsyncContainer = this.cosmosAsyncDatabase
                .getContainer(CONTAINER_ID);            
        } catch (CosmosException e) {
            log.error(e.getMessage());
        }
    }

    public HttpResponseMessage requestCosmosDB(
        String id,
        HttpRequestMessage<Optional<String>> request
    ) {
        CosmosItemResponse<Object> response = this.cosmosAsyncContainer.deleteItem(id, new PartitionKey(id))
            .doOnSubscribe(onSubscribe -> {
                log.info("onSubscribe");
            })
            .block();
        if (Integer.valueOf(response.getStatusCode()) == 204) {
            return request.createResponseBuilder(HttpStatus.ACCEPTED)
                .body("Delete Complete !!")
                .build();
        }
        return request.createResponseBuilder(HttpStatus.ACCEPTED)
            .header("Content-Type", "application/json")
            .body(null)
            .build();
    }
}