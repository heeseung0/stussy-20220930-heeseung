package com.stussy.stussyclone20220930heeseung.service.admin;

import com.stussy.stussyclone20220930heeseung.dto.admin.CategoryResponseDto;
import com.stussy.stussyclone20220930heeseung.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

}
