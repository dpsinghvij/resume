package com.resume.davinder.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility:MutableLiveData<Int>? ){
    val parentActivity= view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}