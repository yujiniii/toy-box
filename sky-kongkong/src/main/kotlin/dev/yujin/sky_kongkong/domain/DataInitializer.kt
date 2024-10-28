package dev.yujin.sky_kongkong.domain

import dev.yujin.sky_kongkong.domain.entity.Seat
import dev.yujin.sky_kongkong.domain.entity.User
import dev.yujin.sky_kongkong.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

// bean 등록을 알려주는 어노테이션
@Component
@Profile(value = ["default"])
class DataInitializer(
    private val usageRepository: UsageRepository,
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository,
    private val userTimeRepository: UserTimeRepository,
    private val seatRepository: SeatRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @PostConstruct
    fun initializeData(){
        println("실행맨")

        val seats = mutableListOf<Seat>()
        for (i in 1..40) {
            seats.add(Seat(i, false))
        }
        seatRepository.saveAll(seats)

        val pw = passwordEncoder.encode("test")
        val tester = User("test", "01011112222", pw)
        userRepository.save(tester)
    }
}