package com.services.useractions;

import java.util.List;

import com.model.entities.Groceries;
import com.model.entities.UserOrder;

public interface UserActionsService {

    /**
     * Get only the available groceries (which are items with inventory more than 0)
     * 
     * @return A list of groceries with Name, Price and Quantity.
     */
    List<Groceries> getAvailableGroceries();

    /**
     * Accepts a list of order id and quantity.
     * Reduces the actual inventory in the DB for the items purchased and outputs
     * the total price of the order along with the items purchased.
     * 
     * @param groceriesOrdered List of groceries ordered by the user.
     * @return String displaying the order summary and total cost.
     */
    String placeOrder(List<UserOrder> groceriesOrdered);

}
