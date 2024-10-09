package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
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