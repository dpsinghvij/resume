package com.resume.davinder.network

import com.resume.davinder.model.ResumeData
import com.resume.davinder.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET


interface ResumeApi{

    @GET(Constants.RESUME_ENDPT)
    fun getResume(): Observable<ResumeData>

}