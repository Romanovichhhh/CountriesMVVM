package com.example.countriesmvvm.domain.models

data class Continent(
    val code : String,
    val name : String,
    val countries : List<Pair<String, String>>
)
