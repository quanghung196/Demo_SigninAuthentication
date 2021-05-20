package com.example.demo_signinauthentication.viewmodel


import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.facebook.*
import com.facebook.login.LoginManager

class SigninFragmentViewModel : ViewModel() {

    fun isLoggedIn(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        return isLoggedIn
    }

    fun logOutUser() {
        LoginManager.getInstance().logOut()
    }

    fun loginWithFacebook(fragment: Fragment) {
        LoginManager.getInstance()
            .logInWithReadPermissions(fragment, listOf("public_profile", "email"))
    }
}