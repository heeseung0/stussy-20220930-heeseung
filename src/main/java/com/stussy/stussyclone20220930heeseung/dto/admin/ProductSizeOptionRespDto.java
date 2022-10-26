package com.stussy.stussyclone20220930heeseung.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductSizeOptionRespDto {
    private int sizeId;
    private String sizeName;
}
