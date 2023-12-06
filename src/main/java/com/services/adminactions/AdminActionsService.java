package com.services.adminactions;

import java.util.List;

import com.model.entities.Groceries;

public interface AdminActionsService {
    /**
     * Add new Grocery Items to the DB. This function accepts the Groceries object
     * with has the following fields
     * <ul>
     * <li>Name - The Name of Grocery Item</li>
     * <li>Price - Cost of the Grocery Item</li>
     * <li>Inventory - Number of items currently available</li>
     * </ul>
     * This function needs all the three fields populated. If any of the field is
     * null or empty then the function will throw an error specifying the fields
     * that are missing. Also it will throw an error if there are duplicates in the
     * list.
     * 
     * @param groceryItems List of GroceryItems to be added to the DB.
     * @return A response string that will give confirmation of addition or contain
     *         the error string.
     */
    public String addGroceryItems(List<Groceries> groceryItems);

    /**
     * Get all the grocery items saved in the DB. This function accepts no
     * paramters. It will find all the grocery items in DB.
     * 
     * @return A list of Grocery Items with Name, Price and Inventory.
     */
    public Object getAllGroceryItems();

    /**
     * Deletes the Grocery Items from DB that have the ids specified as part of the
     * list to be deleted.
     * 
     * @param groceryItemIds List of Grocery Item ids that need to be deleted.
     */
    public String deleteGroceryItemsById(List<Integer> groceryItemIds);

    /**
     * Update the details of the grocery items like Name and Price.
     * <br>
     * <ul>
     * <li>Requires the ids to be pass as part of the Groceries list. If
     * id is not specified it will throw an error and prompt to specify the id.</li>
     * <li>Will ignore updates to the Inventory. This controller is specifically to
     * only update the Name and Price.</li>
     * <li>If any of the Name or Price field is empty it will ignore the update for
     * that field.
     * 
     * @param updateGroceryItems list of grocery items that need to be updated.
     */
    public String updateGroceryItems(List<Groceries> updateGroceryItems);

    /**
     * Update the inventory of the grocery items.
     * <br>
     * <ul>
     * <li>Requires the ids to be pass as part of the Groceries list. If
     * id is not specified it will throw an error and prompt to specify the id.</li>
     * <li>Will ignore updates to the Name and Price. This controller is
     * specifically to
     * only update the Inventory.</li>
     * <li>If Inventory field is empty it will ignore the update for
     * that field.
     * 
     * @param updateGroceryItems list of groceryItems to be updated
     */
    public String updateInventory(List<Groceries> updateGroceryItems);
}
