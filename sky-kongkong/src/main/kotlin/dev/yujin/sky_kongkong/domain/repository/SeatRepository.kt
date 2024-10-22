package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Int> {
    fun countSeatByIsActiveIs(isActive: Boolean): Long
}
