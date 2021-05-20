package com.example.demo_signinauthentication.view.fragment

import android.content.Intent
import android.util.Log

import androidx.navigation.Navigation
import com.example.demo_signinauthentication.R
import com.example.demo_signinauthentication.databinding.FragmentSigninBinding

import com.example.demo_signinauthentication.viewmodel.SigninFragmentViewModel
import com.example.mvvm_retrofit_room.view.base.BaseFragment

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class SigninFragment : BaseFragment<FragmentSigninBinding, SigninFragmentViewModel>() {

    private lateinit var mCallbackManager: CallbackManager


    override fun getLayoutId(): Int = R.layout.fragment_signin

    override fun getViewModel(): Class<SigninFragmentViewModel> =
        SigninFragmentViewModel::class.java

    override fun onViewReady() {
        binding.handleSiginFrmEvent = this
        binding.viewmodel = viewModel

        mCallbackManager = CallbackManager.Factory.create()

        if (viewModel.isLoggedIn()) {
            Log.d("LoggedIn? :", "YES")
            goToHomeScreen()
        } else {
            Log.d("LoggedIn? :", "NO")
            // Show the Home Activity
        }

        // Callback registration
        LoginManager.getInstance()
            .registerCallback(mCallbackManager, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("TAG", "Success Login")
                    goToHomeScreen()
                }

                override fun onCancel() {
                    Log.d("TAG", "Cancel Login")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("TAG", "Error Login")
                }
            })
    }

    private fun goToHomeScreen() {
        val action = SigninFragmentDirections.actionSigninFragmentToHomeFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    fun onLoginFacebookButtonclicked() {
        viewModel.loginWithFacebook(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(FacebookSdk.isFacebookRequestCode(requestCode)){
            mCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }
}