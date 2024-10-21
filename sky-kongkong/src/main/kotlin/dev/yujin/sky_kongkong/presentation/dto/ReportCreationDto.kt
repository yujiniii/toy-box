package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.constant.ReportType
import dev.yujin.sky_kongkong.domain.entity.Report
import dev.yujin.sky_kongkong.domain.entity.User


data class ReportCreationDto(
    val userId: Long,
    val isSolved: Boolean,
    val category: ReportType,
    val detail: String,
) {

    fun toEntity(user: User): Report {
        return Report(
            isSolved = this.isSolved,
            user = user, // User 객체를 전달
            category = this.category.name,
            detail = this.detail
        )
    }
}
