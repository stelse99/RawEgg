package com.example.rawegg.utils

data class RandomUser(
    val name : String = "저축예금",
    val description: String = "잔액보기",
    val profileImage: String = "https://randomuser.me/api/portraits/women/72.jpg"
)

object DummyDataProvider {

    val userList = List(200){ RandomUser() }

}


