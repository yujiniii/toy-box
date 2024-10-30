package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByPhoneIs(phone: String): Optional<User>

    override fun findById(id: Long): Optional<User>
}
