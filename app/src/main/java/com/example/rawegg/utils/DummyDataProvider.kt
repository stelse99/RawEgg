package com.example.rawegg.utils

data class RandomUser(
    val name : String = "개발하는 정대리 🙊",
    val description: String = "오늘도 빡코딩 하고 계신가요?",
    val profileImage: String = "https://randomuser.me/api/portraits/women/72.jpg"
)

object DummyDataProvider {

    val userList = List(200){ RandomUser() }

}


