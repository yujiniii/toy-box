package dev.yujin.sky_kongkong.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    name: String,
    phone: String,
    password: String
) : BaseEntity(
) {
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
}