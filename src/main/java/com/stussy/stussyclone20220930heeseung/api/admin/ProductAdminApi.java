package com.stussy.stussyclone20220930heeseung.api.admin;

import com.stussy.stussyclone20220930heeseung.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930heeseung.aop.annotation.ValidAspect;
import com.stussy.stussyclone20220930heeseung.dto.CMRespDto;
import com.stussy.stussyclone20220930heeseung.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930heeseung.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) throws Exception{
        productManagementService.registerMst(productRegisterReqDto);
        return ResponseEntity.created(null).body(new CMRespDto<>("Register Successfully", true));
    }

     @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception{
        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully",productManagementService.getCategoryList()));
    }
}
