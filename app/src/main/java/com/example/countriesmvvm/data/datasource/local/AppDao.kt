package com.example.countriesmvvm.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.countriesmvvm.domain.models.Continent

@Dao
interface AppDao {

    @Query("SELECT * FROM continents")
    suspend fun getContinentsList(): List<Continent>

    @Insert(onConflict = REPLACE)
    suspend fun saveContinentsList(list: List<Continent>)

}