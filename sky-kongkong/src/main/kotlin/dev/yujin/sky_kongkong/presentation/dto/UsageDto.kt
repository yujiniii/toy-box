package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import java.time.LocalDateTime


data class UsageDto(
    val usageId: Long? = 0,
    val seatId: Int = 0,
    val checkIn: LocalDateTime = LocalDateTime.now(),
    val useMinutes: Number? = 0,
    val checkOut: LocalDateTime? = LocalDateTime.now()
) {
    constructor(usage: Usage) : this(
        usageId = usage.usageId,
        seatId = usage.seatId,
        checkIn = usage.checkIn,
        checkOut = usage.checkOut,
        useMinutes = usage.useMinutes
    )
}
