package dev.yujin.sky_kongkong.domain

import dev.yujin.sky_kongkong.domain.repository.ReportRepository
import dev.yujin.sky_kongkong.domain.repository.UsageRepository
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.domain.repository.UserTimeRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

// bean 등록을 알려주는 어노테이션
@Component
@Profile(value = ["default"])
class DataInitializer(
    private val usageRepository: UsageRepository,
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository,
    private val userTimeRepository: UserTimeRepository
) {
    @PostConstruct
    fun initializeData(){
        println("실행맨")
    }
}