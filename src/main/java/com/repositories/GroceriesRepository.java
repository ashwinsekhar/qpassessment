package com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.entities.Groceries;

@Repository
public interface GroceriesRepository extends JpaRepository<Groceries, Integer> {

    @Query("SELECT g FROM Groceries g WHERE g.inventory > :minInventory")
    List<Groceries> findAvailableGroceries(@Param("minInventory") int minInventory);
}