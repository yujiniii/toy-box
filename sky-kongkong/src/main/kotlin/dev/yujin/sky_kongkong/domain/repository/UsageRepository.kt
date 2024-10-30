package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsageRepository : JpaRepository<Usage, Long> {

    override fun findById(id: Long): Optional<Usage>

    fun findByIsActiveAndUser_UserIdIs(isActive:Boolean, userId: Long): Usage?

    fun findByIsActiveAndUsageIdIs(isActive:Boolean, usageId: Long): Optional<Usage>
}

