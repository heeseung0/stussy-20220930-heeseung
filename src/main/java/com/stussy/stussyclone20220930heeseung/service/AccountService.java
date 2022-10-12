package com.stussy.stussyclone20220930heeseung.service;

import com.stussy.stussyclone20220930heeseung.dto.RegisterReqDto;

public interface AccountService {
    public void register(RegisterReqDto registerReqDto) throws Exception;
}
