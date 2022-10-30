package org.capybara.capybarabackend.item.web.rest;

import org.capybara.capybarabackend.item.model.ItemModel;
import org.capybara.capybarabackend.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/items")
public class ItemController {

    //<editor-fold desc="Fields">

    private final ItemService itemService;

    //</editor-fold>

    //<editor-fold desc="Constructors">

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //</editor-fold>

    //<editor-fold desc="Public methods">

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ItemRequest itemRequest) {
        ItemModel itemModel = newItemModel(itemRequest);
        itemService.saveItem(itemModel);
        return ResponseEntity.ok().build();
    }

    //</editor-fold>

    //<editor-fold desc="Private methods">

    private ItemModel newItemModel(ItemRequest itemRequest) {
        ItemModel itemModel = new ItemModel();
        itemModel.setName(itemRequest.getName());
        return itemModel;
    }

    //</editor-fold>

}
