package com.favpic.security.config

import com.favpic.security.filters.JWTGeneratorFilter
import com.favpic.security.filters.JWTValidatorFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun defaultSecurityConfig(http: HttpSecurity) : SecurityFilterChain =
        http
            .sessionManagement {
                it.sessionCreationPolicy(STATELESS)
            }
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/test/hi").permitAll()
                    .requestMatchers("/test/secure", "/users" ).authenticated()
                    .anyRequest().permitAll()
            }
            .addFilterBefore(JWTValidatorFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAfter(JWTGeneratorFilter(), BasicAuthenticationFilter::class.java)
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .build()

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

}