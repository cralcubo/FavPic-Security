package com.favpic.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun defaultSecurityConfig(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .oauth2Login(Customizer.withDefaults())
            .build()

//    @Bean
//    fun clientRepository(): ClientRegistrationRepository =
//        InMemoryClientRegistrationRepository(
//            CommonOAuth2Provider.GITHUB
//                .getBuilder("github")
//                .clientId("60afd0494af9c68633af")
//                .clientSecret("79841f1af2b09a32631163a983e15d23b9e69a63")
//                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//                .build()
//        )

//    @Bean
//    fun passwordEncoder(): PasswordEncoder =
//        BCryptPasswordEncoder()

//    private fun googleClientRegistration(): ClientRegistration {
//        return ClientRegistration.withRegistrationId("google")
//            .clientId("google-client-id")
//            .clientSecret("google-client-secret")
//            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//            .scope("openid", "profile", "email", "address", "phone")
//            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//            .userNameAttributeName(IdTokenClaimNames.SUB)
//            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//            .clientName("Google")
//            .build()
//    }

}