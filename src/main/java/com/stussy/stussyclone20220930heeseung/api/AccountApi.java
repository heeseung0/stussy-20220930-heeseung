package com.stussy.stussyclone20220930heeseung.api;

import com.stussy.stussyclone20220930heeseung.aop.annotation.LogAspect;
import com.stussy.stussyclone20220930heeseung.dto.CMRespDto;
import com.stussy.stussyclone20220930heeseung.dto.RegisterReqDto;
import com.stussy.stussyclone20220930heeseung.dto.validation.ValidationSequence;
import com.stussy.stussyclone20220930heeseung.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) throws Exception {
        accountService.duplicateEmail(registerReqDto);
        accountService.register(registerReqDto);
        return ResponseEntity.created(URI.create("/account/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail()));
    }
}
