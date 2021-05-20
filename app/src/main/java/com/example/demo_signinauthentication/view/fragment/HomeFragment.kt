package com.example.demo_signinauthentication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.demo_signinauthentication.R
import com.example.demo_signinauthentication.databinding.FragmentHomeBinding
import com.example.demo_signinauthentication.model.UserAuthenProfile
import com.example.demo_signinauthentication.viewmodel.HomeFragmentViewModel
import com.example.demo_signinauthentication.viewmodel.SigninFragmentViewModel
import com.example.mvvm_retrofit_room.view.base.BaseFragment
import com.facebook.AccessToken
import kotlinx.coroutines.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    private var mUserAuthenProfile = UserAuthenProfile()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getViewModel(): Class<HomeFragmentViewModel> =
        HomeFragmentViewModel::class.java

    override fun onViewReady() {
        binding.handleHomeFrmEvent = this
        binding.userAuthenProfile = mUserAuthenProfile

        getUserProfileTypeFacebook()
    }

    private fun getUserProfileTypeFacebook(){
        val accessToken = AccessToken.getCurrentAccessToken()
        val userID = accessToken.userId

        GlobalScope.launch {
            viewModel.getFacebookUserProfile(accessToken, userID, mUserAuthenProfile)
            delay(1000L)
            withContext(Dispatchers.Main){
                binding.userAuthenProfile = mUserAuthenProfile
            }
        }
    }
}