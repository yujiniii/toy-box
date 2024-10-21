package dev.example.portfolio.presentation.service

import dev.example.portfolio.domain.entity.Introduction
import dev.example.portfolio.presentation.repository.PresentationRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class PresentationServiceTest{
  @InjectMocks
  lateinit var presentationService: PresentationService

  @Mock
  lateinit var presentationRepository: PresentationRepository

   val DATA_SIZE = 9

    @Test
    fun testGetIntroductions(){
        // given
        val introductions = mutableListOf<Introduction>()
        for (i in 1..DATA_SIZE){
            val introduction = Introduction(content = "${i}", isActive = i%2==0)
            introductions.add(introduction)
        }

        val activeIntroduction = introductions.filter { introduction: Introduction -> introduction.isActive }

        Mockito
            .`when`(presentationRepository.getActiveIntroduction())
            .thenReturn(activeIntroduction)

        // when
        val introductionDTOs = presentationService.getIntroductions()

        // then
        assertThat(introductionDTOs).hasSize(DATA_SIZE/2)

        for (introductionDTO in introductionDTOs) {
            assertThat(introductionDTO.content.toInt()).isEven()
        }
    }

}