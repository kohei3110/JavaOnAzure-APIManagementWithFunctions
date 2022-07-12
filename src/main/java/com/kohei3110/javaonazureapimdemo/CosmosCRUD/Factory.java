package com.kohei3110.javaonazureapimdemo.CosmosCRUD;

import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.CreateItemRepository;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.DeleteItemRepository;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.GetAllItemRepository;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.GetItemRepository;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.repository.UpdateItemRepository;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.CreateItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.DeleteItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.GetAllItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.GetItemService;
import com.kohei3110.javaonazureapimdemo.CosmosCRUD.service.UpdateItemService;

public class Factory {
    
    private GetItemRepository getItemRepository;
    private GetItemService getItemService;
    private DeleteItemRepository deleteItemRepository;
    private DeleteItemService deleteItemService;
    private CreateItemRepository createItemRepository;
    private CreateItemService createItemService;
    private UpdateItemRepository updateItemRepository;
    private UpdateItemService updateItemService;
    private GetAllItemRepository getAllItemRepository;
    private GetAllItemService getAllItemService;

    public Factory() {
        this.getItemRepository = new GetItemRepository();
        this.getItemService = new GetItemService(this.getItemRepository);
        this.deleteItemRepository = new DeleteItemRepository();
        this.deleteItemService = new DeleteItemService(this.deleteItemRepository);
        this.createItemRepository = new CreateItemRepository();
        this.createItemService = new CreateItemService(this.createItemRepository);
        this.updateItemRepository = new UpdateItemRepository();
        this.updateItemService = new UpdateItemService(this.updateItemRepository);
        this.getAllItemRepository = new GetAllItemRepository();
        this.getAllItemService = new GetAllItemService((this.getAllItemRepository));
    }

    public GetItemService injectGetItemService() {
        return this.getItemService;
    }

    public DeleteItemService injectDeleteItemService() {
        return this.deleteItemService;
    }

    public CreateItemService injectCreateItemService() {
        return this.createItemService;
    }

    public UpdateItemService injectUpdateItemService() {
        return this.updateItemService;
    }

    public GetAllItemService injectGetAllItemService() {
        return this.getAllItemService;
    }
}
