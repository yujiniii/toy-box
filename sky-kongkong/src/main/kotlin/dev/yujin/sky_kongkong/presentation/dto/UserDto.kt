package dev.yujin.sky_kongkong.presentation.dto

import dev.yujin.sky_kongkong.domain.entity.User



class UserDto(
    val userId: Long?,
    val name: String,
    val phone: String
) {
   constructor(user: User) :  this (
       name = user.name,
       userId = user.userId,
       phone = user.phone

   )
}
