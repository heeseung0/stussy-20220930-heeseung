package com.stussy.stussyclone20220930heeseung.service.admin;

import com.stussy.stussyclone20220930heeseung.domain.ProductDetail;
import com.stussy.stussyclone20220930heeseung.dto.admin.*;
import com.stussy.stussyclone20220930heeseung.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930heeseung.exception.CustomValidationException;
import com.stussy.stussyclone20220930heeseung.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<CategoryResponseDto>();
        productManagementRepository.getCategoryList().forEach(category -> {
            categoryResponseDtos.add(category.toDto());
        });
        return categoryResponseDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception{
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");
        }
    }

    @Override
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception {
        List<ProductMstOptionRespDto> list = new ArrayList<ProductMstOptionRespDto>();

        productManagementRepository.getProductMstList().forEach(pdtMst -> {
            list.add(pdtMst.toDto());
        });

        return list;
    }

    @Override
    public List<?> getSizeList(int productId) throws Exception {
        List<ProductSizeOptionRespDto> list = new ArrayList<ProductSizeOptionRespDto>();
        productManagementRepository.getSizeList(productId).forEach(size -> {
            list.add(size.toDto());
        });

        return list;
    }

    @Override
    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.findProductColor(productRegisterDtlReqDto.toEntity()) > 0){
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error","이미 등록된 색상입니다.");
            throw new CustomValidationException("Duplicated Error", errorMap);
        }
    }

    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0){
            throw new CustomInternalServerErrorException("상품 등록 오류");
        }else{
            ProductDetail entity = productRegisterDtlReqDto.toEntity();
            if(entity.getId() == 0 || entity.getSize_id() == 0 || entity.getPdt_color().equals("") || entity.getPdt_stock() == 0){
                Map<String, String> errorMap = new HashMap<String, String>();
                errorMap.put("error","상품 정보가 잘못되었습니다.");
                throw new CustomValidationException("Input Error", errorMap);
            }
        }
    }
}
