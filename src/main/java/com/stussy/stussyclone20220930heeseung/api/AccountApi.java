package com.stussy.stussyclone20220930heeseung.api;

import com.stussy.stussyclone20220930heeseung.dto.RegisterReqDto;
import com.stussy.stussyclone20220930heeseung.dto.validation.ValidationSequence;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) {
        System.out.println("회원가입 요청 데이터:" + registerReqDto);

        //        람다 안 쓰기로해서 주석 처리
//        if(bindingResult.hasErrors()){
//            bindingResult.getFieldErrors().forEach(error -> {
//                System.out.println(error.getField()+" : "+error.getDefaultMessage());
//            });
//        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
//                if(fieldError.getField().equals("password"))  //비밀번호 테스트용
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }
        return ResponseEntity.created(null).body(null);
    }
}
