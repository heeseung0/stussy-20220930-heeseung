package com.stussy.stussyclone20220930heeseung.service.admin;

import com.stussy.stussyclone20220930heeseung.dto.admin.CategoryResponseDto;
import com.stussy.stussyclone20220930heeseung.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930heeseung.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImp implements ProductManagementService{

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        /*
        여기서 부터 시작 (리스트로 변환)
         */
        productManagementRepository.getCategoryList();
        return null;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {

    }
}
