package com.resume.davinder.injection

import com.resume.davinder.network.ResumeApi
import com.resume.davinder.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule{
    /***
     * Provides a ResumeApi object
     */
    @Provides
    @Singleton
    internal fun getResumeApi(retrofit: Retrofit):ResumeApi{
        return retrofit.create(ResumeApi::class.java)
    }

    @Provides
    @Singleton
    internal fun getRetrofitApi():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.gitEndpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}