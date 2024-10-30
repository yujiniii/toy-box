package dev.yujin.sky_kongkong.presentation.dto


data class UserTimeUpdateDto(
    val hours: Int = 0
){
    fun toMinutes(): Int {
        return hours * 60
    }
}
