package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.User
import dev.yujin.sky_kongkong.domain.entity.UserTime
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserTimeRepository : JpaRepository<UserTime, Long> {

    fun findByUser_UserId(userId: Long):Optional<UserTime>
}
