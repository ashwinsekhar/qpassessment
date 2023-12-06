package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.entities.Groceries;
import com.repositories.GroceriesRepository;

@Service
public class DBActionsRepository {

    @Autowired
    GroceriesRepository groceriesRepository;

    public void saveAll(List<Groceries> groceriesToSave) {
        groceriesRepository.saveAll(groceriesToSave);
    }

    public List<Groceries> findAllGroceryItems() {
        return groceriesRepository.findAll();
    }

    public void deleteByIds(List<Integer> groceryItemIds) {
        groceriesRepository.deleteAllById(groceryItemIds);
    }

    public Optional<Groceries> findById(int groceryItemId) {
        return groceriesRepository.findById(groceryItemId);
    }

    public List<Groceries> findAvailableGroceries() {
        return groceriesRepository.findAvailableGroceries(0);
    }

}
