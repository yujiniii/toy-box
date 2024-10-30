package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.User

data class UserLoginDto(
    val phone: String,
    val password: String
) {
    constructor(user: User) : this(
        phone = user.phone,
        password = user.password
    )
}
