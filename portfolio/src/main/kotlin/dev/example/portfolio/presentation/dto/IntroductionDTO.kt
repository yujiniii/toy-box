package dev.example.portfolio.presentation.dto

import dev.example.portfolio.domain.entity.Introduction

class IntroductionDTO (
    val content: String
){
    constructor(introduction: Introduction): this (
        content = introduction.content
    )
}