package com.practice.jwtsecurity.controller;

import com.practice.jwtsecurity.model.JwtUser;
import com.practice.jwtsecurity.security.JwtGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtGenerator jwtGenerator;

    /**
     * user will get jwt token using this "/token" api
     * for a user providing userInfo
     * @param jwtUser userInfo
     * @return jsonWebToken for userInfo
     */
    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {
        return jwtGenerator.generate(jwtUser);
    }
}
