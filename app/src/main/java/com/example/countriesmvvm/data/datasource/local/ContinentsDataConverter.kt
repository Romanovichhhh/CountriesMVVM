package com.example.countriesmvvm.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContinentsDataConverter {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromCountriesList(list: List<Pair<String, String>>) : String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToCountriesList(json: String) : List<Pair<String, String>> {
        val listType = object : TypeToken<List<Pair<String, String>>>() {}.type
        return gson.fromJson(json, listType)
    }
}