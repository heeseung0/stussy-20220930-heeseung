package com.stussy.stussyclone20220930heeseung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CheckoutRespDto {
    private int pdtDtlId;
    private int pdtId;
    private String pdtName;
    private int pdtPrice;
    private String pdtColor;
    private int sizeId;
    private String sizeName;
    private String saveName;

}
