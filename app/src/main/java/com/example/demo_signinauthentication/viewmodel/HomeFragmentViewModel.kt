package com.example.demo_signinauthentication.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.demo_signinauthentication.model.UserAuthenProfile
import com.facebook.*

class HomeFragmentViewModel : ViewModel() {


    @SuppressLint("LongLogTag")
    fun getFacebookUserProfile(token: AccessToken?, userId: String?, userAuthenProfile: UserAuthenProfile) {
        val parameters = Bundle()
        parameters.putString(
            "fields",
            "id, first_name, middle_name, last_name, name, picture, email"
        )
        GraphRequest(token,
            "/$userId/",
            parameters,
            HttpMethod.GET,
            GraphRequest.Callback { response ->
                val jsonObject = response.jsonObject

                // Facebook Access Token
                // You can see Access Token only in Debug mode.
                // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
                if (BuildConfig.DEBUG) {
                    FacebookSdk.setIsDebugEnabled(true)
                    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
                }
                userAuthenProfile.userAccessToken = token.toString()

                // Facebook Id
                if (jsonObject.has("id")) {
                    val facebookId = jsonObject.getString("id")
                    Log.i("Facebook Id: ", facebookId.toString())
                    userAuthenProfile.userID = facebookId.toString()
                } else {
                    Log.i("Facebook Id: ", "Not exists")
                    userAuthenProfile.userID = "Not exists"
                }

                // Facebook First Name
                if (jsonObject.has("first_name")) {
                    val facebookFirstName = jsonObject.getString("first_name")
                    Log.i("Facebook First Name: ", facebookFirstName)
                    userAuthenProfile.userFirstName = facebookFirstName
                } else {
                    Log.i("Facebook First Name: ", "Not exists")
                    userAuthenProfile.userFirstName = "Not exists"
                }

                // Facebook Middle Name
                if (jsonObject.has("middle_name")) {
                    val facebookMiddleName = jsonObject.getString("middle_name")
                    Log.i("Facebook Middle Name: ", facebookMiddleName)
                    userAuthenProfile.userMiddleName = facebookMiddleName
                } else {
                    Log.i("Facebook Middle Name: ", "Not exists")
                    userAuthenProfile.userMiddleName = "Not exists"
                }

                // Facebook Last Name
                if (jsonObject.has("last_name")) {
                    val facebookLastName = jsonObject.getString("last_name")
                    Log.i("Facebook Last Name: ", facebookLastName)
                    userAuthenProfile.userLastName = facebookLastName
                } else {
                    Log.i("Facebook Last Name: ", "Not exists")
                    userAuthenProfile.userLastName = "Not exists"
                }

                // Facebook Name
                if (jsonObject.has("name")) {
                    val facebookName = jsonObject.getString("name")
                    Log.i("Facebook Name: ", facebookName)
                    userAuthenProfile.userName = facebookName
                } else {
                    Log.i("Facebook Name: ", "Not exists")
                    userAuthenProfile.userName = "Not exists"
                }

                // Facebook Profile Pic URL
                if (jsonObject.has("picture")) {
                    val facebookPictureObject = jsonObject.getJSONObject("picture")
                    if (facebookPictureObject.has("data")) {
                        val facebookDataObject = facebookPictureObject.getJSONObject("data")
                        if (facebookDataObject.has("url")) {
                            val facebookProfilePicURL = facebookDataObject.getString("url")
                            Log.i("Facebook Profile Pic URL: ", facebookProfilePicURL)
                            userAuthenProfile.userImageProfile = facebookProfilePicURL
                        }
                    }
                } else {
                    Log.i("Facebook Profile Pic URL: ", "Not exists")
                    userAuthenProfile.userImageProfile = "Not exists"
                }

                // Facebook Email
                if (jsonObject.has("email")) {
                    val facebookEmail = jsonObject.getString("email")
                    Log.i("Facebook Email: ", facebookEmail)
                    userAuthenProfile.userEmail = facebookEmail
                } else {
                    Log.i("Facebook Email: ", "Not exists")
                    userAuthenProfile.userEmail = "Not exists"
                }
                Log.i("User profile: ", userAuthenProfile.toString())
            }).executeAsync()
    }
}