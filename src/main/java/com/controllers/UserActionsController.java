package com.controllers;

import java.util.List;

import com.exceptions.AdminActionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.entities.UserOrder;
import com.repositories.GroceriesRepository;
import com.services.useractions.UserActionsService;

import static com.constants.ResponseConstant.*;

@RestController
@RequestMapping("/api/user")
public class UserActionsController {

    @Autowired
    GroceriesRepository groceriesRepository;

    @Autowired
    UserActionsService userActionsService;

    @GetMapping(value = "/view", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAvailableGroceries() {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return ResponseEntity.ok(userActionsService.getAvailableGroceries());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString());
        }
    }

    /**
     * RestAPI Body example -
     * [{ "orderId": 1, "quantity": 5 }, { "orderId": 4, "quantity":2 }]
     */
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public String placeOrder(@RequestBody List<UserOrder> groceriesOrdered) {
        StringBuilder rStringBuilder = new StringBuilder();
        try {
            return userActionsService.placeOrder(groceriesOrdered);
        } catch (AdminActionsException ae) {
            return rStringBuilder.append(CAUGHT_EXCEPTION).append(ae.getMessage()).toString();
        } catch (Exception e) {
            return rStringBuilder.append(UNCAUGHT_EXCEPTION).append(e.getMessage()).toString();
        }
    }

}
