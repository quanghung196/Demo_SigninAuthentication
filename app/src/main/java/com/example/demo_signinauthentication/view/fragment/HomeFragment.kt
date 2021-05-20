package com.example.demo_signinauthentication.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.demo_signinauthentication.R
import com.example.demo_signinauthentication.databinding.FragmentHomeBinding
import com.example.demo_signinauthentication.model.UserAuthenProfile
import com.example.demo_signinauthentication.view.fragment.SigninFragment.Companion.TYPE_FACEBOOK
import com.example.demo_signinauthentication.viewmodel.HomeFragmentViewModel
import com.example.mvvm_retrofit_room.view.base.BaseFragment
import com.facebook.AccessToken
import kotlinx.coroutines.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    private var mUserAuthenProfile = UserAuthenProfile()
    private val mArgs: HomeFragmentArgs by navArgs();
    private var loginType: Int? = null

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getViewModel(): Class<HomeFragmentViewModel> =
        HomeFragmentViewModel::class.java

    override fun onViewReady() {
        loginType = mArgs.loginType
        binding.handleHomeFrmEvent = this
        binding.userAuthenProfile = mUserAuthenProfile

        viewModel.getUserProfile(loginType!!)

        viewModel.userProfile.observe(viewLifecycleOwner, Observer {
            binding.userAuthenProfile = it
        })
    }

}