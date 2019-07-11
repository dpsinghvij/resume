package com.resume.davinder.viewmodels

import android.arch.lifecycle.ViewModel
import com.resume.davinder.injection.DaggerViewModelInjector
import com.resume.davinder.injection.NetworkModule
import com.resume.davinder.injection.ViewModelInjector
import com.resume.davinder.utils.Constants


abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector= DaggerViewModelInjector
        .builder().networkModule(NetworkModule).build()

    init {
        inject()
    }

    private fun inject() {
        if(this is ResumeViewModel){
            injector.inject(this)
        }
    }
}