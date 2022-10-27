package com.stussy.stussyclone20220930heeseung.service.admin;

import com.stussy.stussyclone20220930heeseung.domain.ProductDetail;
import com.stussy.stussyclone20220930heeseung.dto.admin.*;
import com.stussy.stussyclone20220930heeseung.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220930heeseung.exception.CustomValidationException;
import com.stussy.stussyclone20220930heeseung.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    @Value("${file.path}")  //application.yml 에 지정된 값을 들고옴 (AutoWired(객체)의 value형 어노테이션)
    private String filePath;

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
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if (productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0) {
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
        if (productManagementRepository.findProductColor(productRegisterDtlReqDto.toEntity()) > 0) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 색상입니다.");
            throw new CustomValidationException("Duplicated Error", errorMap);
        }
    }

    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if (productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 오류");
        } else {
            ProductDetail entity = productRegisterDtlReqDto.toEntity();
            if (entity.getId() == 0 || entity.getSize_id() == 0 || entity.getPdt_color().equals("") || entity.getPdt_stock() == 0) {
                Map<String, String> errorMap = new HashMap<String, String>();
                errorMap.put("error", "상품 정보가 잘못되었습니다.");
                throw new CustomValidationException("Input Error", errorMap);
            }
        }
    }

    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
//        log.info("pdtID >>> " + productImgReqDto.getPdtId());

        if (productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않았습니다.");
            throw new CustomValidationException("Img Error", errorMap);
        }

        productImgReqDto.getFiles().forEach(file -> {
//            log.info("fileName >>> " + file.getOriginalFilename());
            String originName = file.getOriginalFilename();
            String extension = originName.substring(originName.lastIndexOf("."));
            String saveName = UUID.randomUUID().toString().replaceAll("-", "") + extension;

            Path path = Paths.get(filePath + "product/" + saveName);

            File f = new File(filePath + "product");
            if(!f.exists()){
                f.mkdirs();
            }

            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new CustomInternalServerErrorException(e.getMessage());
            }
        });
    }
}
