package com.ndangducbn.zmart.model.builder;


import com.ndangducbn.zmart.constants.BusinessConstants;
import com.ndangducbn.zmart.model.origin.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuilderPurchase {
    private String firstName;
    private String lastName;
    private String customerId;
    private String creditCardNumber;
    private String itemPurchased;
    private int quantity;
    private double price;
    private Long purchaseDate;
    private String zipCode;
    private String department;
    private String employeeId;
    private String storeId;


    public BuilderPurchase maskCreditCard() {
        Objects.requireNonNull(this.creditCardNumber, "Credit Card can't be null");
        String[] parts = this.creditCardNumber.split("-");
        if (parts.length < 4) {
            this.creditCardNumber = BusinessConstants.NUMBER_HIDE;
        } else {
            String last4Digits = this.creditCardNumber.split("-")[3];
            this.creditCardNumber = BusinessConstants.CREDIT_NUMBER_HIDE + last4Digits;
        }
        return this;
    }

    public Purchase toPurchase() {
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(this, purchase);
        return purchase;
    }
}
