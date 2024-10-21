package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import java.time.LocalDateTime
import java.util.Optional


data class UsageCreationDto (
    val deskNumber: Number,
    val checkIn: LocalDateTime,
) {
    fun toEntity(user: User): Usage {
        return Usage(
            user = user,
            deskNumber = deskNumber,
            checkIn = LocalDateTime.now(),
        )
    }
}
