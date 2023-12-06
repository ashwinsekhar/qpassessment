package com.services.adminactions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.exceptions.AdminActionsException;
import com.model.entities.Groceries;
import com.services.DBActionsRepository;
import com.utils.Validator;

import static com.constants.ResponseConstant.*;

@Service
public class AdminActionsServiceImpl implements AdminActionsService {

    @Autowired
    DBActionsRepository dbActionsRepository;

    @Override
    public String addGroceryItems(List<Groceries> groceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        List<String> itemNames = new ArrayList<>();
        List<Groceries> groceriesToSave = new ArrayList<>();
        for (Groceries groceryItem : groceryItems) {
            boolean isValid = Validator.validateParameters(groceryItem);
            if (isValid) {
                String itemName = groceryItem.getName();
                if (!itemNames.contains(itemName))
                    itemNames.add(itemName);
                else {
                    String errorMsg = DUPLICATE_GROCERY_ITEM + itemName;
                    throw new AdminActionsException(errorMsg);
                }
                groceriesToSave.add(groceryItem);
            } else {
                throw new AdminActionsException(INVALID_PARAMETERS);
            }
        }

        saveGroceryItems(groceriesToSave);

        rStringBuilder.append(ADDING_ITEMS_TO_DB).append(itemNames);
        return rStringBuilder.toString();
    }

    @Override
    public List<Groceries> getAllGroceryItems() {
        return dbActionsRepository.findAllGroceryItems();
    }

    @Override
    public String deleteGroceryItemsById(List<Integer> groceryItemIds) {
        StringBuilder rStringBuilder = new StringBuilder();

        if (!groceryItemIds.isEmpty()) {
            dbActionsRepository.deleteByIds(groceryItemIds);
            rStringBuilder.append(DELETE_GROCERY_PART1).append(groceryItemIds)
                    .append(DELETE_GROCERY_PART2);
        } else {
            rStringBuilder.append(DELETE_GROCERY_PART3);
        }
        return rStringBuilder.toString();
    }

    @Override
    public String updateGroceryItems(List<Groceries> updateGroceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        Set<String> itemNames = new HashSet<>();
        StringBuilder missingItemsBuilder = new StringBuilder();

        List<Groceries> groceriesToSave = new ArrayList<>();
        for (Groceries updateGroceryItem : updateGroceryItems) {
            int itemId = updateGroceryItem.getId();
            Optional<Groceries> existingItemOP = dbActionsRepository.findById(itemId);
            if (existingItemOP.isPresent()) {
                updatedDetailsForItem(updateGroceryItem, existingItemOP.get(), itemNames, groceriesToSave);
            } else {
                missingItemsBuilder.append(ITEM_ID).append(itemId).append("\n");
            }
        }

        saveGroceryItems(groceriesToSave);

        generateResponseString(itemNames, rStringBuilder, ADDING_ITEMS_TO_DB, missingItemsBuilder);

        return rStringBuilder.toString();
    }

    @Override
    public String updateInventory(List<Groceries> updateGroceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        StringBuilder missingItemsBuilder = new StringBuilder();

        Set<String> itemNames = new HashSet<>();
        List<Groceries> groceriesToSave = new ArrayList<>();

        for (Groceries grocery : updateGroceryItems) {
            int itemId = grocery.getId();
            Optional<Groceries> existingItemOP = dbActionsRepository.findById(itemId);
            if (existingItemOP.isPresent()) {
                updatedInventoryForItem(grocery, existingItemOP.get(), itemNames, groceriesToSave);
            } else {
                missingItemsBuilder.append(ITEM_ID).append(itemId).append("\n");
            }
        }

        saveGroceryItems(groceriesToSave);

        generateResponseString(itemNames, rStringBuilder, UPDATED_INVENTORY, missingItemsBuilder);

        return rStringBuilder.toString();
    }

    private static void updatedInventoryForItem(Groceries grocery, Groceries existingItem, Set<String> itemNames,
            List<Groceries> groceriesToSave) {
        if (grocery.getInventory() != null && grocery.getInventory() >= 0)
            existingItem.setInventory(grocery.getInventory());

        itemNames.add(existingItem.getName());
        groceriesToSave.add(existingItem);
    }

    private static void updatedDetailsForItem(Groceries updateGroceryItem, Groceries existingItem,
            Set<String> itemNames, List<Groceries> groceriesToSave) {
        if (StringUtils.hasText(updateGroceryItem.getName()))
            existingItem.setName(updateGroceryItem.getName());
        if (updateGroceryItem.getPrice() != null && updateGroceryItem.getPrice() >= 0)
            existingItem.setPrice(updateGroceryItem.getPrice());

        itemNames.add(existingItem.getName());
        groceriesToSave.add(existingItem);
    }

    private static void generateResponseString(Set<String> itemNames, StringBuilder rStringBuilder, String headerString,
            StringBuilder missingItemsBuilder) {
        if (!itemNames.isEmpty()) {
            rStringBuilder.append(headerString).append(itemNames);
        }
        if (!missingItemsBuilder.isEmpty()) {
            rStringBuilder.append(ITEMS_NOT_FOUND).append(missingItemsBuilder);
        }
    }

    private void saveGroceryItems(List<Groceries> groceriesToSave) {
        if (!CollectionUtils.isEmpty(groceriesToSave))
            dbActionsRepository.saveAll(groceriesToSave);
    }

}
