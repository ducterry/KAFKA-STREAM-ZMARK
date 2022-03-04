package com.ndangducbn.zmart.model.data;

import com.ndangducbn.zmart.model.origin.Purchase;

import java.util.Arrays;
import java.util.List;

public class DataFake {

    public static final List<Purchase> data = Arrays.asList(
            Purchase.builder()
                    .firstName("DUC")
                    .lastName("NGUYEN DANG")
                    .quantity(1000)
                    .creditCardNumber("1234-456-789")
                    .customerId("HNI1000001")
                    .department("COFFEE")
                    .employeeId("SHB0280291")
                    .itemPurchased("BOOK").price(1)
                    .purchaseDate(1641969637548L)
                    .storeId("SHBFC01")
                    .zipCode("100000")
                    .build(),
            Purchase.builder()
                    .firstName("DUY")
                    .lastName("NGUYEN DANG")
                    .quantity(1000)
                    .creditCardNumber("1234-456-123")
                    .customerId("HNI1000002")
                    .department("IT")
                    .employeeId("SHB0280292")
                    .itemPurchased("MOBILEPHONE").price(3)
                    .purchaseDate(1641969637548L)
                    .storeId("SHBFC01")
                    .zipCode("100000")
                    .build(),
            Purchase.builder()
                    .firstName("DUNG")
                    .lastName("NGUYEN DANG")
                    .quantity(1000)
                    .creditCardNumber("1234-456-123-1221")
                    .customerId("HNI1000003")
                    .department("ELECTRONICS")
                    .employeeId("SHB0280293")
                    .itemPurchased("MOBILEPHONE").price(10)
                    .purchaseDate(1641969637548L)
                    .storeId("SHBFC01")
                    .zipCode("100000")
                    .build(),
            Purchase.builder()
                    .firstName("TRUNG")
                    .lastName("NGUYEN DANG")
                    .quantity(1000)
                    .creditCardNumber("1234-456-123-5671")
                    .customerId("HNI1000004")
                    .department("ELECTRONICS")
                    .employeeId("SHB0280294")
                    .itemPurchased("MOBILEPHONE").price(100)
                    .purchaseDate(1641969637548L)
                    .storeId("SHBFC02")
                    .zipCode("100000")
                    .build()
    );
}
