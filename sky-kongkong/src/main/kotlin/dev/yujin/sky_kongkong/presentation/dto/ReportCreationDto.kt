package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.constant.ReportType
import dev.yujin.sky_kongkong.domain.entity.Report
import dev.yujin.sky_kongkong.domain.entity.User


data class ReportCreationDto(
    val category: ReportType=ReportType.User,
    val detail: String = "",
) {

    fun toEntity(user: User): Report {
        return Report(
            isSolved = false,
            user = user, // User 객체를 전달
            category = this.category.name,
            detail = this.detail
        )
    }
}
