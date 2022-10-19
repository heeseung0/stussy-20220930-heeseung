package com.stussy.stussyclone20220930heeseung.dto.admin;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class CategoryResponseDto {
    private int id;
    private String name;
}
