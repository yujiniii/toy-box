package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_time")
class UserTime(
    remainMinutes: Int
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "user_time_id") /* column mapping */
    var userTimeId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */

    @Column(name = "remain_minutes")
    var remainMinutes: Int = remainMinutes

    // User와의 연관관계 설정
    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var user: User

    // remainMinutes 값을 업데이트하는 메서드 추가
    fun updateRemainMinutes(newRemainMinutes: Int) {
        this.remainMinutes = newRemainMinutes
    }
}