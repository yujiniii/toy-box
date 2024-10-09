package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.cglib.core.Local
import java.time.LocalDateTime

@Entity
class Usage(
    userId: Long,
    deskNumber: Number,
    checkIn: LocalDateTime,
    useMinutes: Number?,
    checkOut: LocalDateTime?

) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "usage_id") /* column mapping */
    var usageId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */


    @Column(name = "user_id")
    var userId: Long = userId

    @Column(name = "desk_number")
    var deskNumber: Number = deskNumber

    @Column(name = "check_in")
    var checkIn: LocalDateTime = checkIn

    @Column(nullable = true, name = "use_minutes")
    var useMinutes: Number? = useMinutes

    @Column(nullable = true, name = "check_out")
    var checkOut: LocalDateTime? = checkOut
}