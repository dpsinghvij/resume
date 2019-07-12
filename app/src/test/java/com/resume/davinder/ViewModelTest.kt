package com.resume.davinder

import android.arch.lifecycle.ViewModelProviders
import com.resume.davinder.viewmodels.AppDetailViewModel
import com.resume.davinder.views.AppDetailActivity
import com.resume.davinder.views.MainActivity
import org.junit.Assert
import org.junit.Test


class ViewModelTest {



    @Test
    fun validate_url(){
        /*val app: AppDetailActivity= Mockito.mock(AppDetailActivity::class.java)*/
        val appDetailViewModel= AppDetailViewModel()//ViewModelProviders.of(app).get(AppDetailViewModel::class.java)
        Assert.assertTrue(appDetailViewModel.validateUrl("https://playstore.google.com"))
        Assert.assertFalse(appDetailViewModel.validateUrl("xcdfl://aystore.google.com"))
        Assert.assertTrue(appDetailViewModel.validateUrl("www.google.com"))

    }
}