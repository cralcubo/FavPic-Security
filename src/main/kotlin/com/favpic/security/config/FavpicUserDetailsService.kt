package com.favpic.security.config

import com.favpic.security.dao.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class FavpicUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val users = userRepository.findAllByUserName(username)
        val user = when {
            users.isEmpty() -> throw UsernameNotFoundException("No user found")
            users.size > 1 -> throw IllegalStateException("More than one user (${users.size}) were found with the username: $username")
            else -> users.first()
        }
        return User.withUsername(username)
            .password(user.password)
            .roles("ADMIN")
            .build()
    }
}