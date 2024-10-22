package dev.yujin.sky_kongkong.presentation.interceptor

import org.apache.coyote.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime


@RestControllerAdvice
class GlobalExceptionHandler {

    // 커스텀 BadRequestException 예외 처리
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf(
            "timestamp" to LocalDateTime.now().toString(),
            "status" to HttpStatus.BAD_REQUEST.value().toString(),
            "error" to "Bad Request",
            "message" to (ex.message ?: "잘못된 요청입니다."),
            "path" to ""  // 여기에 API 경로를 추가하고 싶으면 HttpServletRequest에서 경로를 받아 추가 가능
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    // 기타 예외 처리 (필요에 따라 추가 가능)
}