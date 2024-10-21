package dev.example.portfolio.presentation.dto

import org.springframework.context.annotation.Description

// data transfer Object

data class AchievementDTO (
    val title: String,
    val description: String,
    val host: String,
    val achievedDate: String?

)