package dev.yujin.sky_kongkong.presentation.service

import dev.yujin.sky_kongkong.domain.entity.Report
import dev.yujin.sky_kongkong.domain.repository.ReportRepository
import dev.yujin.sky_kongkong.domain.repository.UserRepository
import dev.yujin.sky_kongkong.presentation.dto.ReportCreationDto
import dev.yujin.sky_kongkong.presentation.dto.ReportDto
import dev.yujin.sky_kongkong.presentation.dto.UserDto
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReportService(
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository
) {


    @Transactional(readOnly = false)
    fun createNewReport(userId: Long, dto: ReportCreationDto): String {
        val user = userRepository.findById(userId).orElseThrow {
            throw BadRequestException("해당 사용자를 찾을 수 없습니다.")
        }
        val report = dto.toEntity(user)
        reportRepository.save(report)
        return "ok"
    }


    @Transactional(readOnly = false)
    fun getReportById(userId: Long, reportId: Long): ReportDto {
        val report = reportRepository.findById(reportId).orElseThrow()

        if (report.user.userId != userId) {
            throw BadRequestException("본인이 작성한 문의만 조회할 수 있습니다")
        }

        return ReportDto(report)
    }
}