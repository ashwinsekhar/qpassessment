package com.services.useractions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.exceptions.AdminActionsException;
import com.model.entities.Groceries;
import com.model.entities.UserOrder;
import com.services.DBActionsRepository;
import com.utils.Validator;

import static com.constants.ResponseConstant.*;

@Service
public class UserActionsServiceImpl implements UserActionsService {

    @Autowired
    DBActionsRepository dbActionsRepository;

    @Override
    public List<Groceries> getAvailableGroceries() {
        return dbActionsRepository.findAvailableGroceries();
    }

    @Override
    public String placeOrder(List<UserOrder> groceriesOrdered) {
        List<Groceries> groceriesToSave = new ArrayList<>();
        StringBuilder orderSummaryBuilder = new StringBuilder();
        StringBuilder rStringBuilder = new StringBuilder();
        StringBuilder missingItemsBuilder = new StringBuilder();
        double totalPriceOfTheOrder = 0d;
        for (UserOrder grocery : groceriesOrdered) {
            boolean isValid = Validator.validateParameters(grocery);
            if (isValid) {
                totalPriceOfTheOrder += processOrder(grocery, orderSummaryBuilder, groceriesToSave,
                        missingItemsBuilder);
            } else {
                throw new AdminActionsException(INVALID_PARAMETERS_ORDER);
            }
        }

        String formattedOrderTotal = formatTotalInIndianCurrency(totalPriceOfTheOrder);

        if (!CollectionUtils.isEmpty(groceriesToSave))
            dbActionsRepository.saveAll(groceriesToSave);

        rStringBuilder.append(generateResponseString(orderSummaryBuilder, formattedOrderTotal, missingItemsBuilder));
        return rStringBuilder.toString();
    }

    private double processOrder(UserOrder grocery, StringBuilder orderSummaryBuilder, List<Groceries> groceriesToSave,
            StringBuilder missingItemsBuilder) {
        Optional<Groceries> existingItemOP = dbActionsRepository.findById(grocery.getOrderId());
        double totalPriceOfTheOrder = 0d;
        if (existingItemOP.isPresent()) {
            Groceries existingItem = existingItemOP.get();
            int leftOverInventory = existingItem.getInventory() - grocery.getQuantity();
            if (existingItem.getInventory() > 0 && leftOverInventory >= 0) {
                orderSummaryBuilder.append(ORDER_PROCESS_PART1).append(existingItem.getName())
                        .append(ORDER_PROCESS_PART2).append(grocery.getQuantity())
                        .append("\n");
                existingItem.setInventory(leftOverInventory);
                groceriesToSave.add(existingItem);
                totalPriceOfTheOrder += (existingItem.getPrice() * grocery.getQuantity());
            } else {
                missingItemsBuilder.append(ITEM_ID).append(grocery.getOrderId()).append("\n");
            }
        } else {
            missingItemsBuilder.append(ITEM_ID).append(grocery.getOrderId()).append("\n");
        }
        return totalPriceOfTheOrder;
    }

    private static String generateResponseString(StringBuilder orderSummaryBuilder, String formattedOrderTotal,
            StringBuilder missingItemsBuilder) {
        StringBuilder responseBuilder = new StringBuilder();
        if (!orderSummaryBuilder.isEmpty()) {
            responseBuilder.append(ORDER_PROCESS_PART3).append(orderSummaryBuilder)
                    .append(ORDER_PROCESS_PART4).append(ORDER_PROCESS_PART5)
                    .append(formattedOrderTotal);
        }

        if (!missingItemsBuilder.isEmpty()) {
            responseBuilder.append("\n").append(ITEM_OUT_OF_STOCK)
                    .append(missingItemsBuilder);
        }

        return responseBuilder.toString();
    }

    private static String formatTotalInIndianCurrency(double totalPriceOfTheOrder) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        return currencyFormat.format(totalPriceOfTheOrder);
    }
}
