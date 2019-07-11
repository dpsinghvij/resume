package com.resume.davinder.model

import java.io.Serializable

data class ResumeData(
    val summary: String,
    val education: ArrayList<Education>,
    val workexperience: ArrayList<Work>,
    val apps: ArrayList<App>
)

data class Education (
    val university:String,
    val degree: String,
    val year: String
)

data class Work (
    val company:String,
    val title: String,
    val roles: ArrayList<String>,
    val year: String
)


data class App(
    val title: String,
    val description:String,
    val link:String,
    val features: ArrayList<String>
):Serializable