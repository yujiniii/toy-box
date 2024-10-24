package dev.example.portfolio.domain

import dev.example.portfolio.domain.constant.SkillType
import dev.example.portfolio.domain.entity.Achievement
import dev.example.portfolio.domain.entity.Experience
import dev.example.portfolio.domain.entity.ExperienceDetail
import dev.example.portfolio.domain.entity.Introduction
import dev.example.portfolio.domain.entity.Link
import dev.example.portfolio.domain.entity.Project
import dev.example.portfolio.domain.entity.ProjectDetail
import dev.example.portfolio.domain.entity.ProjectSkill
import dev.example.portfolio.domain.entity.Skill
import dev.example.portfolio.domain.repository.AchievementRepository
import dev.example.portfolio.domain.repository.ExperienceRepository
import dev.example.portfolio.domain.repository.IntroductionRepository
import dev.example.portfolio.domain.repository.LinkRepository
import dev.example.portfolio.domain.repository.ProjectRepository
import dev.example.portfolio.domain.repository.SkillRepository
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val experienceRepository: ExperienceRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val projectRepository: ProjectRepository,
    private val skillRepository: SkillRepository,
) {

    val log = LoggerFactory.getLogger(DataInitializer::class.java)

    @PostConstruct
    fun initializeData() {

        log.info("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        // achievement 초기화
        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "정보처리기사",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2024, 9, 10),
                isActive = true
            ),
            Achievement(
                title = "동양미래 EXPO 졸업작품전 우수상",
                description = "AOB(All of Bike) 라는 자전거 편의 서비스를 동아리 대표로 출품하여 우수상을 수상했습니다. ",
                host = "동양미래대학교",
                achievedDate = LocalDate.of(2021, 11, 5),
                isActive = true
            ),
            Achievement(
                title = "리눅스마스터 2급",
                description = "리눅스 일반, 리눅스 운영 및 관리, 리눅스 활용",
                host = "한국정보통신진흥협회",
                achievedDate = LocalDate.of(2020, 10, 7),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        // introduction 초기화
        val introductions = mutableListOf<Introduction>(
            Introduction(content = "친화력을 타고난 개발자, 서유진입니다.", isActive = true),
            Introduction(content = "자신의 부족한 점을 알고 더 많이 배우기 위해 노력합니다.", isActive = true),
            Introduction(content = "개발에 있어 가장 중요하게 생각하는 부분은 꼼꼼한 설계입니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        // link 초기화
        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/yujiniii", isActive = true),
            Link(name = "Linkedin", content = "https://www.linkedin.com/in/yujiniii-a3376724a", isActive = true),
        )
        linkRepository.saveAll(links)

        // experience / experience_detail 초기화
        val experience1 = Experience(
            title = "동양미래대학교",
            description = "컴퓨터공학 전공",
            startYear = 2019,
            startMonth = 3,
            endYear = 2023,
            endMonth = 2,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.3/4.5", isActive = true),
                ExperienceDetail(content = "전공동아리 EL 학술부장으로 2021 한국 전자전 출품", isActive = true),
                ExperienceDetail(content = "반대표 경험 다수", isActive = true)
            )
        )
        val experience2 = Experience(
            title = "EJN",
            description = "백엔드 개발자",
            startYear = 2023,
            startMonth = 1,
            endYear = 2024,
            endMonth = 6,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "신규 서비스 R&D", isActive = true),
                ExperienceDetail(content = "EZ TWIP 개발", isActive = true),
                ExperienceDetail(content = "백오피스 개발", isActive = true),
                ExperienceDetail(content = "TWIP-뽑기후원 개발", isActive = true)
            )
        )
        experienceRepository.saveAll(mutableListOf(experience2, experience1))

        // skill 초기화
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val typescript = Skill(name = "typescript", type = SkillType.LANGUAGE.name, isActive = true)
        val javascript = Skill(name = "javascript", type = SkillType.LANGUAGE.name, isActive = true)
        val php = Skill(name = "php", type = SkillType.LANGUAGE.name, isActive = true)
        val codeigniter = Skill(name = "codeigniter", type = SkillType.FRAMEWORK.name, isActive = true)
        val nodejs = Skill(name = "Express", type = SkillType.FRAMEWORK.name, isActive = true)
        val nestjs = Skill(name = "NestJS", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
        val webstorm = Skill(name = "webstorm", type = SkillType.TOOL.name, isActive = true)
        val vscode = Skill(name = "vscode", type = SkillType.TOOL.name, isActive = true)
        val postman = Skill(name = "postman", type = SkillType.TOOL.name, isActive = true)
        val swagger = Skill(name = "swagger", type = SkillType.TOOL.name, isActive = true)
        skillRepository.saveAll(
            mutableListOf(
                kotlin,
                spring,
                mysql,
                redis,
                typescript,
                nestjs,
                nodejs,
                javascript,
                webstorm,
                postman,
                vscode,
                swagger,
                php,
                codeigniter
            )
        )

        // project / project_detail / project_skill 초기화

        val project1 = Project(
            name = "스카이 콩콩",
            description = "스터디카페의 좌석 현황 및 불편신고, 시간충전 등을 키오스크까지 가지 않고 웹을 통해 편하게 사용할 수 있게 하는 서비스",
            startYear = 2024,
            startMonth = 10,
            endYear = 2024,
            endMonth = 10,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "kotlin과 spring boot 를 이용한 MVC 아키텍처 웹 서비스 개발", url = null, isActive = true),
                ProjectDetail(content = "PasswordEncoder 를 이용해 DB에 password 를 암호화해 저장", url = null, isActive = true),
                ProjectDetail(content = "thymeleaf 의 fragment 를 이용해 중복 코드 최소화", url = null, isActive = true),
                ProjectDetail(content = "Github Repository", url = "https://github.com/yujiniii/toy-box/tree/main/sky-kongkong", isActive = true),
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = kotlin),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
            )
        )
        val project2 = Project(
            name = "달대표",
            description = "동네 주민 배달 대행 서비스와 동네 영수증 리뷰를 할 수 있는 어플리케이션입니다.",
            startYear = 2022,
            startMonth = 3,
            endYear = 2022,
            endMonth = 11,
            isActive = true
        )

        project2.addDetails(
            mutableListOf(
                ProjectDetail(content = "node.js Express 로 영수증 리뷰 파트를 구현하였습니다.", url = null, isActive = true),
                ProjectDetail(content = "과거 동아리 대표 경험을 살려, 팀장으로서 기획, 발표, 보고서 작성 등을 문제없이 진행하였습니다.", url = null, isActive = true),
                ProjectDetail(content = "Github Repository", url = "https://github.com/yujiniii/DeliveryDelegate-back", isActive = true)
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = nodejs),
                ProjectSkill(project = project2, skill = javascript),
                ProjectSkill(project = project2, skill = mysql)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}
