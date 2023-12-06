package com.controllers;

import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.AdminActionsException;
import com.model.entities.Groceries;
import com.services.adminactions.AdminActionsService;

import static com.constants.ResponseConstant.*;

@RestController
@RequestMapping("/api/admin")
public class AdminActionsController {

    @Autowired
    AdminActionsService adminActionsService;

    /**
     * RestAPI body example - [{ "name": "Sugar", "price": 50.00, "inventory": 10 },
     * { "name": "Salt", "price": 30.00, "inventory": 20 }]
     */
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertData(@RequestBody List<Groceries> groceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return adminActionsService.addGroceryItems(groceryItems);
        } catch (AdminActionsException ae) {
            return rStringBuilder.append(CAUGHT_EXCEPTION).append(ae.getMessage()).toString();
        } catch (JDBCException je) {
            return JDBC_EXCEPTION_ADD;
        } catch (Exception e) {
            return rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString();
        }
    }

    @GetMapping(value = "/groceries", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getGroceryList() {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return ResponseEntity.ok(adminActionsService.getAllGroceryItems());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(rStringBuilder.append(SQL_EXCEPTION).append(e.getMessage()).toString());
        }
    }

    /**
     * RestAPI body example - [1, 2, 3]
     */
    @PostMapping(value = "/deleteByIds", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteGroceryItemsById(@RequestBody List<Integer> groceryItemIds) {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return adminActionsService.deleteGroceryItemsById(groceryItemIds);
        } catch (JDBCException je) {
            return JDBC_EXCEPTION_DELETE;
        } catch (Exception e) {
            return rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString();
        }
    }

    /**
     * RestAPI body example - [{ "id": 1, "name": "Sugar", "price": 60.00 },
     * { "id": 2, "name": "Salt", "price": 40.00 }]
     */
    @PostMapping(value = "/updateDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateGroceryItems(@RequestBody List<Groceries> updateGroceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return adminActionsService.updateGroceryItems(updateGroceryItems);
        } catch (Exception e) {
            return rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString();
        }
    }

    /**
     * RestAPI body example - [{ "id": 1, "inventory": 10 }, { "id": 2, "inventory":
     * 20 }]
     */
    @PostMapping(value = "/updateInventory", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateInventory(@RequestBody List<Groceries> updateGroceryItems) {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return adminActionsService.updateInventory(updateGroceryItems);
        } catch (Exception e) {
            return rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString();
        }
    }
}
