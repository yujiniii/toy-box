package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.Report


data class ReportDto(
    val isSolved: Boolean,
    val category: String,
    val detail: String,
) {
   constructor(report: Report) :  this (
       isSolved = report.isSolved,
       category = report.category.name,
       detail = report.detail
   )
}
