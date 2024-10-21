package dev.example.portfolio.presentation.dto

import dev.example.portfolio.domain.entity.Skill

data class SkillDTO (
    val name: String,
    val type: String,
){
    constructor(skill: Skill): this(
        name = skill.name,
        type = skill.type.name
    )
}