package com.example.demo_signinauthentication.model

import java.io.Serializable

data class UserAuthenProfile(
    var userID: String = "",
    var userFirstName: String = "",
    var userMiddleName: String = "",
    var userLastName: String = "",
    var userName: String = "",
    var userImageProfile: String = "",
    var userEmail: String = "",
    var userAccessToken: String = ""
) : Serializable