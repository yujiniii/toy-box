package dev.yujin.sky_kongkong.domain.repository

import org.junit.jupiter.api.*
import dev.yujin.sky_kongkong.domain.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest(
    @Autowired val userRepository: UserRepository
) {

    val DATA_SIZE = 10

    private fun createUser(n:Int):User {
        val user = User(
            name = "yujin${n}",
            phone = "01011112222",
            password = "123",
        )
        return user
    }

    @BeforeAll
    fun beforeAll(){
        println("test before")
        val beforeInit = userRepository.findAll()
        assertThat(beforeInit).hasSize(0)

        var users = mutableListOf<User>()
        for( i in 1 .. DATA_SIZE){
            val user = createUser(i)
            users.add(user)
        }
        userRepository.saveAll(users)
    }

    @Test
    fun testFindAll(){
        val users = userRepository.findAll()
        assertThat(users).hasSize(DATA_SIZE)
        println("users size: ${users.size}")
    }
}