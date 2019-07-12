package com.resume.davinder.viewmodels

import com.resume.davinder.utils.Constants
import java.lang.StringBuilder
import java.util.regex.Pattern

class AppDetailViewModel: BaseViewModel(){

    fun processStringList(featureList:List<String>):String{
        val stringBuilder= StringBuilder()
        for(string in featureList){
            stringBuilder.append("\u2022 ").append(string).appendln()
        }
        return stringBuilder.toString()
    }

    fun validateUrl(url:String):Boolean{
        val pattern= Constants.URL_REGEX.toRegex()
        return pattern.containsMatchIn(url);
    }
}