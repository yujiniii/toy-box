package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.*
import java.time.Duration
import java.time.LocalDateTime

@Entity
@Table(name = "seat")
class Seat(
    seatId: Int,
    isActive: Boolean,
) : BaseEntity() {
    @Id
    @Column(name = "seat_id")
    var seatId: Int = seatId

    @Column(name = "is_active")
    var isActive: Boolean = isActive

    fun update(
        isActive: Boolean
    ) {
        this.isActive = isActive
    }
}