package dev.yujin.sky_kongkong.domain.repository


import dev.yujin.sky_kongkong.domain.entity.Report
import dev.yujin.sky_kongkong.domain.entity.Usage
import dev.yujin.sky_kongkong.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ReportRepository : JpaRepository<Report, Long> {

    @Query("select p from Report p left join fetch p.user where p.reportId = :id")
    override fun findById(id: Long): Optional<Report>

}
