package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.*


@Entity
class User(
    name: String,
    phone: String,
    password: String
) : BaseEntity() {
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
    var password: String = password

    @OneToOne(
        targetEntity = UserTime::class,
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(name = "user_id")
    var timeInfo: UserTime? = null

    fun update(
        name: String,
        phone: String,
        password: String
    ) {
        this.name = name
        this.phone = phone
        this.password = password
    }
}