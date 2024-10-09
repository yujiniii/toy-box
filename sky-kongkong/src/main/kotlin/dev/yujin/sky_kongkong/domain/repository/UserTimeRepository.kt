package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserTimeRepository : JpaRepository<User, Long> {

}
