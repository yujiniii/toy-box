package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.repository.SeatRepository
import dev.yujin.sky_kongkong.domain.repository.UsageRepository
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.domain.repository.UserTimeRepository
import dev.yujin.sky_kongkong.presentation.dto.*
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.concurrent.thread


@Service
class SeatService(
    private val seatRepository: SeatRepository,
    private val usageRepository: UsageRepository
) {

    @Transactional(readOnly = true)
    fun getSeatInfo(): List<SeatDto> {
        val seats = seatRepository.findAll()
        return seats.map { SeatDto(seatId = it.seatId, isActive = it.isActive) }
    }

    @Transactional(readOnly = true)
    fun getRemainSeatCount(): Long {
        val remainSeatCount = seatRepository.countSeatByIsActiveIs(false)
        return remainSeatCount
    }

    @Transactional(readOnly = true)
    fun getUserReservedSeat(userId: Long): SeatDto? {
        val usage = usageRepository.findByIsActiveAndUser_UserIdIs(true, userId)
        return usage?.let {
            SeatDto(seatId = it.seatId, isActive = it.isActive)
        }
    }
}