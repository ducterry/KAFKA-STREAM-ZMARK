package com.ndangducbn.zmart.model.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasePattern {

    private String zipCode;
    private String item;
    private Long date;
    private double amount;

    @Override
    public String toString() {
        return "PurchasePattern[\n" +
                "\t\t\t-zipCode='" + zipCode +"'" +'\n' +
                "\t\t\t- item='" + item +"'" +'\n' +
                "\t\t\t- date=" + date + '\n' +
                "\t\t\t- amount=" + amount + '\n' +
                ']';
    }
}
