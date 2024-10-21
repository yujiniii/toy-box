package dev.example.portfolio.presentation.repository

import dev.example.portfolio.domain.entity.*
import dev.example.portfolio.domain.repository.*
import org.springframework.stereotype.Repository

// 일종의 파사드 패턴
@Repository
class PresentationRepository(
    private val achievementRepository: AchievementRepository,
    private val experienceRepository: ExperienceRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
) {

    fun getActiveAchievement(): List<Achievement> {
        return achievementRepository.findAllByIsActive(true)
    }

    fun getActiveExperience(): List<Experience> {
        return experienceRepository.findAllByIsActive(true)
    }

    fun getActiveProject(): List<Project> {
        return projectRepository.findAllByIsActive(true)
    }

    fun getActiveLink(): List<Link> {
        return linkRepository.findAllByIsActive(true)
    }

    fun getActiveIntroduction(): List<Introduction> {
        return introductionRepository.findAllByIsActive(true)
    }

    fun getActiveSkills(): List<Skill> {
        return skillRepository.findAllByIsActive(true)
    }
}