package com.utils;

import org.springframework.util.StringUtils;

import com.model.entities.Groceries;
import com.model.entities.UserOrder;

public class Validator {

    public static boolean validateParameters(Object parameter) {
        if (parameter instanceof Groceries groceryObj) {
            return (StringUtils.hasText(groceryObj.getName())
                    && (groceryObj.getPrice() != null && groceryObj.getPrice() >= 0d)
                    && (groceryObj.getInventory() != null && groceryObj.getInventory() >= 0));
        } else if (parameter instanceof UserOrder userOrderObj) {
            return (userOrderObj.getOrderId() != null
                    && userOrderObj.getQuantity() != null && userOrderObj.getQuantity() > 0);
        }
        return false;
    }
}
