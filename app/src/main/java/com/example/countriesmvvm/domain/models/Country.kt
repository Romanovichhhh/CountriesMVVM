package com.example.countriesmvvm.domain.models

data class Country(
    val code : String,
    val name : String,
    val native : String,
    val phone : String,
    val continentName : String,
    val capital : String,
    val currency : String,
    val languages : List<Language>,
    val states : List<String>
    )

data class Language(
    val name : String,
    val native : String
)
