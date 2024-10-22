package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Usage
import java.time.Duration
import java.time.LocalDateTime


data class UsageUpdateDto (
    val checkOut: LocalDateTime,
) {
    fun toEntity(usage: Usage, isActive: Boolean): Usage {
        return Usage(
            user = usage.user,
            seatId = usage.seatId,
            checkIn = usage.checkIn,
            useMinutes = calculateUseMinutes(usage.checkIn, checkOut),
            isActive = isActive,
            checkOut = checkOut,
        )
    }

    private fun calculateUseMinutes(checkIn: LocalDateTime, checkOut: LocalDateTime): Int {
        return  Duration.between(checkIn, checkOut).toMinutes().toInt()
    }


}
