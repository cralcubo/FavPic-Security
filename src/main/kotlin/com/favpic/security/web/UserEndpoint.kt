package com.favpic.security.web

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserEndpoint {

    @GetMapping("users")
    fun getAuthenticatedUser(authentication: OAuth2AuthenticationToken) : String {
        return authentication.name
    }
}

