package com.stussy.stussyclone20220930heeseung.api;

import com.stussy.stussyclone20220930heeseung.dto.CMRespDto;
import com.stussy.stussyclone20220930heeseung.dto.RegisterReqDto;
import com.stussy.stussyclone20220930heeseung.dto.validation.ValidationSequence;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) {
        return ResponseEntity.created(null).body(new CMRespDto<>("회원가입 성공", registerReqDto));
    }
}
