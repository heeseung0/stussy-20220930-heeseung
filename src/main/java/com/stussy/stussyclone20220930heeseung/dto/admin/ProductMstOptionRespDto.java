package com.stussy.stussyclone20220930heeseung.dto.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductMstOptionRespDto {
    private int pdtID;
    private String category;
    private String pdtName;

}
