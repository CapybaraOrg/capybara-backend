package org.capybara.capybarabackend.item.service;

import org.capybara.capybarabackend.item.domain.jpa.ItemEntity;
import org.capybara.capybarabackend.item.model.ItemModel;
import org.capybara.capybarabackend.item.repository.jpa.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
public class ItemService {


    //<editor-fold desc="Fields">

    private final ItemRepository itemRepository;

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    //</editor-fold>

    //<editor-fold desc="Constructors">

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //</editor-fold>

    //<editor-fold desc="Public methods">

    @Transactional
    public void saveItem(@Valid ItemModel itemModel) {
        log.info("saveItem called");
        log.info(itemModel.toString());
        ItemEntity itemEntity = newItemEntity(itemModel);
        itemRepository.saveAndFlush(itemEntity);
    }

    //</editor-fold>

    //<editor-fold desc="Private methods">

    private ItemEntity newItemEntity(ItemModel itemModel) {
        LocalDateTime now = LocalDateTime.now();
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(UUID.randomUUID().toString());
        itemEntity.setCreated(now);
        itemEntity.setModified(now);
        itemEntity.setName(itemModel.getName());
        return itemEntity;
    }

    //</editor-fold>

}
