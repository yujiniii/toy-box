package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun getUsers(): List<UserDto> {
        val users = userRepository.findAll()
        return users.map { UserDto(it) }
    }
}