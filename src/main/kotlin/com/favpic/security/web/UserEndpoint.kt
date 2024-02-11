package com.favpic.security.web

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserEndpoint {

    @GetMapping("users")
    fun getAuthenticatedUser(authentication: Authentication) : String {
        return authentication.name
    }
}

