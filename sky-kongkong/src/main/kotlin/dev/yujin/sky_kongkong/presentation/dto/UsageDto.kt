package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.User
import java.time.LocalDateTime


data class UsageDto (
    val usageId: Long?,
    val seatId: Int,
    val checkIn: LocalDateTime,
    val useMinutes: Number?,
    val checkOut: LocalDateTime?
) {}
