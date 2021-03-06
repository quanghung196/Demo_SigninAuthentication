package com.example.mvvm_retrofit_room.view.base

import android.app.Activity
import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demo_signinauthentication.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment(), CoroutineScope {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    private lateinit var job: Job
    //protected lateinit var customProgressDialog: CustomProgressDialog

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): Class<VM>
    abstract fun onViewReady()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                getLayoutId(), container, false
            )
        binding.lifecycleOwner = this
        job = Job()

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(getViewModel())

        //customProgressDialog = CustomProgressDialog(activity as Activity)

        return binding.root
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}