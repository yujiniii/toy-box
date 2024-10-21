package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import java.time.Duration
import java.time.LocalDateTime


data class UsageUpdateDto (
    val checkOut: LocalDateTime,
) {
    fun toEntity(usage: Usage): Usage {
        return Usage(
            user = usage.user,
            deskNumber = usage.deskNumber,
            checkIn = usage.checkIn,
            useMinutes = calculateUseMinutes(usage.checkIn, checkOut),
            checkOut = checkOut,
        )
    }

    private fun calculateUseMinutes(checkIn: LocalDateTime, checkOut: LocalDateTime): Int {
        return  Duration.between(checkIn, checkOut).toMinutes().toInt()
    }


}
