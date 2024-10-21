package dev.example.portfolio.presentation.controller

import org.assertj.core.api.Assertions.assertThat
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import java.nio.charset.StandardCharsets

@SpringBootTest // boot를 띄운 후에 테스트 진행
@AutoConfigureMockMvc
@DisplayName("api controller test")
class PresentationApiControllerTest(
    @Autowired private val mockMvc: MockMvc
){

    @Test
    @DisplayName("introductions 조회")
    fun testGetIntroductions(){
        // given
        val uri = "/api/v1/introductions"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Resume 조회")
    fun testGetResume(){
        // given
        val uri = "/api/v1/resume"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString) // 응답값이 DTO 배열이 아니기 떄문에

        // then
        assertThat(jsonObject.optJSONArray("experiences").length()).isPositive()
        assertThat(jsonObject.optJSONArray("achievements").length()).isPositive()
        assertThat(jsonObject.optJSONArray("skills").length()).isPositive()
    }

    @Test
    @DisplayName("Projects 조회")
    fun testGetProjects(){
        // given
        val uri = "/api/v1/projects"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Links 조회")
    fun testGetLinks(){
        // given
        val uri = "/api/v1/links"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    private fun performGet(uri:String): MvcResult{
        return mockMvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }
}