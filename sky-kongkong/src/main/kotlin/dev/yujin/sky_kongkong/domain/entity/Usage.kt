package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.*
import java.time.Duration
import java.time.LocalDateTime

@Entity
@Table(name = "usage")
class Usage(
    deskNumber: Number,
    checkIn: LocalDateTime,
    user: User?,
    isActive: Boolean = true,
    useMinutes: Number? = null,  // 기본값 null
    checkOut: LocalDateTime? = null // 기본값 null
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* auto_increment */
    @Column(name = "usage_id") /* column mapping */
    var usageId: Long? = null /* spring에서 null로 보내면, DB에서 auto_increment */

    @Column(name = "desk_number")
    var deskNumber: Number = deskNumber

    @Column(name = "check_in")
    var checkIn: LocalDateTime = checkIn

    @Column(nullable = true, name = "use_minutes")
    var useMinutes: Number? = useMinutes

    @Column(nullable = true, name = "check_out")
    var checkOut: LocalDateTime? = checkOut

    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    lateinit var user: User

    @Column(name = "is_active")
    var isActive: Boolean = isActive

    fun update(
        deskNumber: Number,
        isActive: Boolean,
        checkIn: LocalDateTime,
        useMinutes: Number?,
        checkOut: LocalDateTime?,
    ) {
        this.deskNumber = deskNumber
        this.checkIn = checkIn
        this.useMinutes = useMinutes
        this.checkOut = checkOut
        this.isActive = isActive
    }


}