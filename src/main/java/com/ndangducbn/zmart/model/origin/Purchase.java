package com.ndangducbn.zmart.model.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {
    private String firstName;
    private String lastName;
    private String customerId;
    private String creditCardNumber;
    private String itemPurchased;
    private String department;
    private String employeeId;
    private int quantity;
    private double price;
    private Long purchaseDate;
    private String zipCode;
    private String storeId;

    @Override
    public String toString() {
        return "Purchase[\n" +
                "\t\t\t- firstName='" + firstName +"'" +'\n' +
                "\t\t\t- lastName='" + lastName +"'" +'\n' +
                "\t\t\t- customerId='" + customerId +"'" +'\n' +
                "\t\t\t- creditCardNumber='" + creditCardNumber +"'" +'\n' +
                "\t\t\t- itemPurchased='" + itemPurchased +"'" +'\n' +
                "\t\t\t- department='" + department +"'" +'\n' +
                "\t\t\t- employeeId='" + employeeId +"'" +'\n' +
                "\t\t\t- quantity=" + quantity +'\n' +
                "\t\t\t- price=" + price +'\n' +
                "\t\t\t- purchaseDate=" + purchaseDate +'\n' +
                "\t\t\t- zipCode='" + zipCode +"'" +'\n' +
                "\t\t\t- storeId='" + storeId +"'" +'\n' +
                ']';
    }
}
