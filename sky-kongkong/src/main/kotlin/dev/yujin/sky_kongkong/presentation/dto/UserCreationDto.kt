package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.User

data class UserCreationDto(
    val name: String = "",
    val phone: String = "",
    val password: String = ""
) {
    constructor(user: User) : this(
        name = user.name,
        phone = user.phone,
        password = user.password
    )

    fun toEntity(): User {
        return User(
            name = name,
            phone = phone,
            password = password,
        )
    }
}
