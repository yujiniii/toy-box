package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import java.time.LocalDateTime
import java.util.Optional


data class UsageCreationDto (
    val seatId: Int,
) {
    fun toEntity(user: User): Usage {
        return Usage(
            user = user,
            seatId = seatId,
            checkIn = LocalDateTime.now(),
        )
    }
}
