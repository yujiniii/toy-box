package dev.example.portfolio.domain.repository

import dev.example.portfolio.domain.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ExperienceRepository : JpaRepository<Experience, Long> {

    // JPQL: 자바의 객체지향 쿼리
    // fetch join 을 사용해서 N+1 problem 방지
    // Experience.details 이 없어도 데이터는 보여야 하기 때문에 left join
    @Query("select e from Experience e left join fetch e.details where e.isActive = :isActive")
    fun findAllByIsActive(isActive: Boolean): List<Experience>

    @Query("select e from Experience e left join fetch e.details where e.id = :id")
    override fun findById(id: Long): Optional<Experience>

}
