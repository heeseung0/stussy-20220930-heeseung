package com.stussy.stussyclone20220930heeseung.api;

import com.stussy.stussyclone20220930heeseung.dto.RegisterReqDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
        System.out.println("회원가입 요청 데이터:" +registerReqDto);
        return null;
    }
}
