package com.example.demo_signinauthentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SigninFragmentViewModel::class.java)) {
            return SigninFragmentViewModel() as T
        } else if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel() as T
        }
        throw IllegalAccessException("Unable construct viewModel")
    }
}