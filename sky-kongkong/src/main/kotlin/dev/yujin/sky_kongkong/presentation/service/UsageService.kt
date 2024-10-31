package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.entity.Seat
import dev.yujin.sky_kongkong.domain.repository.SeatRepository
import dev.yujin.sky_kongkong.domain.repository.UsageRepository
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.domain.repository.UserTimeRepository
import dev.yujin.sky_kongkong.presentation.dto.UsageCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UsageDto
import dev.yujin.sky_kongkong.presentation.dto.UsageUpdateDto
import dev.yujin.sky_kongkong.presentation.dto.UserCreationDto
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.concurrent.thread


@Service
class UsageService(
    private val userRepository: UserRepository,
    private val usageRepository: UsageRepository,
    private val seatRepository: SeatRepository

) {

    @Transactional(readOnly = true)
    fun getUserUsage(userId: Long): UsageDto {
        val user = userRepository.findById(userId).orElseThrow {
            BadRequestException("사용자 정보를 확인해주세요")
        }

        val usage = usageRepository.findByIsActiveAndUser_UserIdIs(true, userId)
            ?: throw BadRequestException("먼저 입실해주세요")

        return UsageDto(
            usageId = usage.usageId,
            seatId = usage.seatId,
            checkIn = usage.checkIn,
            checkOut = usage.checkOut,
            useMinutes = usage.useMinutes
        )
    }

    @Transactional(readOnly = false)
    fun enterCafe(userId: Long, seatId: Int): String {
        val user = userRepository.findById(userId).orElseThrow {
            BadRequestException("사용자 정보를 확인해주세요")
        }

        if (user.timeInfo.remainMinutes <= 0) {
            throw BadRequestException("먼저 시간을 충전해주세요")
        }
        val usage = usageRepository.findByIsActiveAndUser_UserIdIs(true, userId)
        println(usage)

        if (usage != null) {
            throw BadRequestException("먼저 퇴실해주세요")
        }

        val seat = seatRepository.findById(seatId).orElseThrow {
            BadRequestException("유효하지 않은 좌석입니다")
        }

        if (seat.isActive) {
            throw BadRequestException("사용중인 좌석입니다.")
        }

        seatRepository.save(
            Seat(
                seatId = seatId,
                isActive = true,
            )
        )

        usageRepository.save(UsageCreationDto(seatId).toEntity(user))

        return "ok"
    }

    @Transactional(readOnly = false)
    fun leaveCafe(userId: Long): String {
        userRepository.findById(userId).orElseThrow {
            throw BadRequestException("사용자 정보를 확인해주세요")
        }

        // get usage
        val usage = usageRepository.findByIsActiveAndUser_UserIdIs(true, userId)
            ?: throw BadRequestException("입실 정보가 존재하지 않습니다")

        if (usage.user?.userId != userId) {
            throw BadRequestException("본인만 퇴실할 수 있습니다")
        }

        seatRepository.save(
            Seat(
                seatId = usage.seatId,
                isActive = false,
            )
        )

        usageRepository.save(UsageUpdateDto(LocalDateTime.now()).toEntity(usage, false))
        return "ok"
    }


}