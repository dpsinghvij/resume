package com.resume.davinder.viewmodels

import java.lang.StringBuilder

class AppDetailViewModel: BaseViewModel(){

    fun processStringList(featureList:List<String>):String{
        val stringBuilder= StringBuilder()
        for(string in featureList){
            stringBuilder.append("\u2022 ").append(string).appendln()
        }
        return stringBuilder.toString()
    }
}