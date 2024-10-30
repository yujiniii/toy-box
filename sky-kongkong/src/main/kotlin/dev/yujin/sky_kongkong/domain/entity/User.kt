package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
@Table(name = "users") // user가 h2에서 예약된 테이블인듯?
class User(
    name: String,
    phone: String,
    password: String
) : BaseEntity(), UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "user_id") /* column mapping */
    var userId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */

    @Column(name = "name", nullable = false)
    var name: String = name

    @Column(name = "phone", nullable = false)
    var phone: String = phone

    @Column(name = "password", nullable = false)
    private var password: String = password

    @OneToOne(
        targetEntity = UserTime::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(name = "user_id")
    lateinit var timeInfo: UserTime

    fun update(
        name: String,
        phone: String,
        password: String
    ) {
        this.name = name
        this.phone = phone
        this.password = password
    }

    // UserTime과의 연관관계 설정 메서드 추가
    fun assignTimeInfo(timeInfo: UserTime) {
        this.timeInfo = timeInfo
        timeInfo.user = this // 양방향 관계 설정
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("USER"))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }
}