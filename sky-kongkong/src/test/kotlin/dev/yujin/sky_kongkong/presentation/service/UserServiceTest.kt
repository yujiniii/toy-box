package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.entity.User
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest (
){

    @InjectMocks
    lateinit var userService: UserService

    @Mock
    lateinit var userRepository: UserRepository


    val DATA_SIZE = 10

    private fun createUser(n:Int): User {
        val user = User(
            name = "yujin${n}",
            phone = "01011112222",
            password = "123",
        )
        return user
    }

    @BeforeEach
    fun beforeEach() {
        Mockito.`when`(userRepository.findAll()).thenReturn(emptyList())

        val users = (1..DATA_SIZE).map { createUser(it) }
        Mockito.`when`(userRepository.findAll()).thenReturn(users)
    }

    @Test
    fun testGetUsers(){
        val usersByMethod = userService.getUsers()
        println(usersByMethod)

        assertThat(usersByMethod).isNotEmpty()

        // 각 UserDto에서 password 프로퍼티가 존재하지 않음을 확인
        usersByMethod.forEach { userDto ->
            assertThat(userDto.javaClass.declaredFields.map { it.name }).doesNotContain("password")
        }
    }
}