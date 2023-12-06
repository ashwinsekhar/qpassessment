package com.constants;

public class ResponseConstant {
    public static final String UNCAUGHT_EXCEPTION = "Uncaught Exception occurred: ";
    public static final String CAUGHT_EXCEPTION = "Exception occurred: ";
    public static final String JDBC_EXCEPTION_ADD = "Please provide the right information for the groceries to be added. " +
            "One or all of the provided groceries already exists or the price and inventory information might be incorrect!";
    public static final String SQL_EXCEPTION = "SQL Exception occurred: ";
    public static final String JDBC_EXCEPTION_DELETE = "Please provide the right ids for the groceries to be deleted."
            + " One or all of the ids provided does not exist or is invalid.";
    public static final String DUPLICATE_GROCERY_ITEM = "Detected duplicates in the grocery items. "
            + "Please specify unique grocery items. First duplicate item found: ";
    public static final String INVALID_PARAMETERS = "Specified parameters are not valid. "
            + "Please specify values for all three parameter correctly (Name, Price and Inventory)";
    public static final String INVALID_PARAMETERS_ORDER = "Specified parameters are not valid. "
            + "Please specify values for all three parameter correctly (OrderId and Quantity)";
    public static final String ADDING_ITEMS_TO_DB = "Following items have be added successfully to DB: \n";
    public static final String UPDATED_INVENTORY = "Following item's inventory has be updated successfully to DB: \n";
    public static final String ITEMS_NOT_FOUND = "\nFollowing item Ids could not be found: \n";
    public static final String DELETE_GROCERY_PART1 = "Grocery Items having ids: ";
    public static final String DELETE_GROCERY_PART2 = " have been deleted successfully";
    public static final String DELETE_GROCERY_PART3 = "Nothing to be deleted!";
    public static final String ITEM_ID = "Item Id: ";
    public static final String ITEM_OUT_OF_STOCK = "Items listed below could not be found or is out of stock!\n";
    public static final String ORDER_PROCESS_PART1 = "Name: ";
    public static final String ORDER_PROCESS_PART2 = " and Quantity: ";
    public static final String ORDER_PROCESS_PART3 = "Your Order of - \n";
    public static final String ORDER_PROCESS_PART4 = "has been successfully placed!!";
    public static final String ORDER_PROCESS_PART5 = " Total Cost of the order: ";
}
