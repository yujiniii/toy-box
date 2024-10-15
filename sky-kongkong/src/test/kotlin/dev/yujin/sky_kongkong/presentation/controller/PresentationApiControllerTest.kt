package dev.yujin.sky_kongkong.presentation.controller

import org.assertj.core.api.Assertions.assertThat
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
class PresentationApiControllerTest: BaseControllerTest() {

    @Test
    @DisplayName("모든 사용자 조회")
    fun testGetMusics() {
        val uri = "/api/test"

        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        assertThat(jsonArray.length()).isPositive()
    }

}