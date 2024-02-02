package com.favpic.security.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestEndpoint {

    @GetMapping("hi")
    fun sayHi() : String {
        return "Hi, Ma!"
    }

    @GetMapping("secure")
    fun sayHello() : String {
        return "Hey security!"
    }

}