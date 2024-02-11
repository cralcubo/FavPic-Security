package com.favpic.security.filters

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*


private const val JWT_KEY = "a0f7c8a60a3fd4d1aefc7b1583d6d3dcaafc09e28d8d97d6c9f8a017c35e5f9b"

class JWTGeneratorFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(JWTGeneratorFilter::class.java)

    @Throws
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("Generating JWT...")
        val jwt = SecurityContextHolder.getContext().authentication?.let {
            val key = Keys.hmacShaKeyFor(JWTKeys.HMAC_KEY.toByteArray())
            val now = Date()
            Jwts.builder()
                .issuer("FavPic App")
                .subject("JWT Token")
                .claim("username", it.name)
                .claim("authorities", "ROLE_ADMIN")
                .issuedAt(now)
                .expiration(Date(now.time + 3600_000))
                .signWith(key).compact()
        }

        response.setHeader("Authorization", jwt)
        response.addCookie(Cookie("Authorization", jwt))

        filterChain.doFilter(request,response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.startsWith("/users")
    }
}