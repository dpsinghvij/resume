package com.resume.davinder.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import com.resume.davinder.R
import com.resume.davinder.model.ResumeData
import com.resume.davinder.network.ResumeApi
import com.resume.davinder.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel class for the MainActivity
 */
class ResumeViewModel: BaseViewModel(){
    @Inject
    lateinit var resumeApi:ResumeApi
    // update the visiblity of progressbar
    val loadingProgressVisibility: MutableLiveData<Int> = MutableLiveData()
    val resumeMutableLiveData: MutableLiveData<ResumeData> = MutableLiveData()
    val intentType: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable
    init {
        loadUserProfile()
    }
    private fun loadUserProfile(){
        subscription= resumeApi.getResume().subscribeOn(Schedulers.io())
            .doOnSubscribe{onLoadStarted()}
            .doOnTerminate{onLoadFinished()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result->onSuccess(result)},{onErrorOccured()})
    }

    fun loadGithubProfile(){
        intentType.value= Constants.GITHUB_INTENT
    }

    fun hireMeAction(){
        intentType.value= Constants.HIRE_ME_INTENT
    }

    private fun onLoadStarted() {
        loadingProgressVisibility.value = View.VISIBLE
        errorMessage.value= null
    }

    private fun onLoadFinished() {
        //loadingProgressVisibility.value = View.GONE
        loadingProgressVisibility.postValue(View.GONE)
    }

    private fun onErrorOccured() {
        errorMessage.value= R.string.error_message

    }

    private fun onSuccess(result: ResumeData) {

        Log.e("",result.toString())
        resumeMutableLiveData.postValue(result)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}