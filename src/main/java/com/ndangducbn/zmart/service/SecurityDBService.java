package com.ndangducbn.zmart.service;

import java.util.Date;

public interface SecurityDBService {
    static void saveRecord(Date date, String employeeId, String item) {
        System.out.println("Canh báo!! Tìm thấy 1 vài vấn đề rủi ro:" +
                "\n   - Ngày:  "+date+
                "\n   - Nhân viên: "+employeeId+
                "\n   - Sản phẩm : "+ item);
    }
}
