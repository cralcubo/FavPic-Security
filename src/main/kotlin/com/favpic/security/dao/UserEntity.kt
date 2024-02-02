package com.favpic.security.dao

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name = "user_id")
    val id: Int? = null,

    @Column(name = "username")
    val userName: String? = null,

    val password: String? = null,
    val name: String? = null,
    val email: String? = null,

    @Column(name = "creation_date")
    val creationDate: LocalDateTime? = null
)