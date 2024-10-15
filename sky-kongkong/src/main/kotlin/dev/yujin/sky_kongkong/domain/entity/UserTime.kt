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
}