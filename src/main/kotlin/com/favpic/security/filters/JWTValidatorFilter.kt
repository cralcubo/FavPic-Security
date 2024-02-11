package com.favpic.security.filters

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets

class JWTValidatorFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(JWTValidatorFilter::class.java)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")
        log.info("Validating jwt: $jwt")
        if (null != jwt) {
            try {
                val key = Keys.hmacShaKeyFor(
                    JWTKeys.HMAC_KEY.toByteArray()
                )

                val claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .body
                val username = claims["username"].toString()
                val authorities = claims["authorities"] as String?

                val auth =
                    UsernamePasswordAuthenticationToken(
                        username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                    )
                SecurityContextHolder.getContext().authentication = auth
            } catch (e: Exception) {
                throw BadCredentialsException("Invalid Token received!", e)
            }
        }
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.startsWith("/users")
    }
}