package com.library.userservice.feignclient;

import com.library.userservice.dto.user.UserAuthenticationRequestDto;
import com.library.userservice.dto.user.UserAuthenticationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Artur Tomeyan
 * @date 17/02/2023
 */
@FeignClient(name = "user-auth-service")
public interface UserFeignClient {

    @PostMapping("/api/v1/user/authenticate")
    UserAuthenticationResponseDto userAuthentication(UserAuthenticationRequestDto authenticationRequestDto);
}