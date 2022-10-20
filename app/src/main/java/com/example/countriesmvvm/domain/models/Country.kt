package com.example.countriesmvvm.domain.models

data class Country(
    val code: String,
    val name: String,
    val native: String,
    val phone: String,
    val continentName: String,
    val capital: String,
    val currency: String,
    val languages: List<String>,
    val states: List<String>,
    val emoji: String?
)

