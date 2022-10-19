package com.stussy.stussyclone20220930heeseung.dto.admin;

import lombok.Data;

@Data
public class ProductRegisterReqDto {
    private String category;
    private String name;

    private int price;
    private String simpleInfo;
    private String detailInfo;
    private String optionInfo;
    private String managementInfo;
    private String shippingInfo;
}
