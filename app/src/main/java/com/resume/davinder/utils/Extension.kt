package com.resume.davinder.utils

import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import java.lang.Exception

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun ViewGroup.inflate(layoutId: Int, attachedToRoot:Boolean= false): View {
    return LayoutInflater.from(context).inflate(layoutId,this,attachedToRoot);
}

fun AppCompatActivity.openUrl(url:String){
    val webIntent = Intent(Intent.ACTION_VIEW)
    webIntent.data = Uri.parse(url)
    try {
        startActivity(webIntent)
    }catch (exception: Exception){
        Toast.makeText(this,"No web browser available", Toast.LENGTH_SHORT).show()
    }

}

fun FloatingActionButton.hideButton() {
    val params = this.layoutParams as CoordinatorLayout.LayoutParams
    val behavior = params.behavior as FloatingActionButton.Behavior?
    if (behavior != null) {
        behavior.isAutoHideEnabled = false
    }
    this.hide()
}