package com.library.userauthenticationservice.userclient;

import com.library.userauthenticationservice.model.UserAuthenticationRequest;
import com.library.userauthenticationservice.model.UserAuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Artur Tomeyan
 * @date 09/02/2023
 */
@FeignClient(name = "user-service", url = "localhost:8082")
public interface UserFeignClient {

    @PostMapping("/api/v1/authenticate")
    UserAuthenticationResponse authenticate(@RequestBody UserAuthenticationRequest userAuthentication);
}