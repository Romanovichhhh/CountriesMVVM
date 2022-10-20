package com.example.countriesmvvm.domain.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "continents", primaryKeys = ["code"])
@Parcelize
data class Continent(
    val code : String,
    val name : String,
    val countries : List<Pair<String, String>>
) : Parcelable
