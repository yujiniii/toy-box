package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.entity.UserTime
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.domain.repository.UserTimeRepository
import dev.yujin.sky_kongkong.presentation.dto.UserCreationDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import dev.yujin.sky_kongkong.presentation.dto.UserLoginDto
import dev.yujin.sky_kongkong.presentation.dto.UserTimeDto
import org.apache.coyote.BadRequestException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userTimeRepository: UserTimeRepository

) {

    @Transactional(readOnly = true)
    fun getUsers(): List<UserDto> {
        val users = userRepository.findAll()
        return users.map { UserDto(it) }
    }

    @Transactional(readOnly = true)
    fun getUserById(userId: Long): UserDto {
        val user = userRepository.findById(userId).orElseThrow()
        return UserDto(user)
    }

    @Transactional(readOnly = true)
    fun login(dto: UserLoginDto): UserDto{
        val user = userRepository.findByPhoneIs(dto.phone).orElseThrow {
            throw BadRequestException("회원가입을 해 주세요")
        }

        if(!passwordEncoder.matches(dto.password, user.password)) {
            throw BadRequestException("비밀번호가 틀렸습니다.")
        }

        // @todo implement session or cookie

        return UserDto(user)
    }

    @Transactional(readOnly = false)
    fun register(dto: UserCreationDto): UserDto {
        val user = userRepository.findByPhoneIs(dto.phone)
        println(user)

        if (!user.isEmpty) {
            throw BadRequestException("이미 회원가입하셨습니다. 로그인해주세요")
        }

        val encodedPassword = passwordEncoder.encode(dto.password)
        println(encodedPassword)
        val newUser = userRepository.save(UserCreationDto(
            name = dto.name,
            phone = dto.phone,
            password = encodedPassword
        ).toEntity())

        val userTime = UserTime(remainMinutes = 0)
        newUser.assignTimeInfo(userTime)  // 연관관계 설정

        return UserDto(newUser)
    }

    @Transactional(readOnly = false)
    fun addTimes(userId: Long, dto: UserTimeDto): String {
        val userTime = userTimeRepository.findByUser_UserId(userId).orElseThrow{
            throw BadRequestException("해당 사용자의 시간 정보를 찾을 수 없습니다.")
        }

        userTime.updateRemainMinutes(dto.remainMinutes)

        userTimeRepository.save(userTime)

        return "ok"
    }

    fun withdraw(userId: Long): String {
        userRepository.deleteById(userId)
        return "ok"
    }
}