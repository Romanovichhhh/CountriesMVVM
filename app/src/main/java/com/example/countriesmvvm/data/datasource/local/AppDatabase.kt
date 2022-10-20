package com.example.countriesmvvm.data.datasource.local

import android.content.Context
import androidx.room.*
import com.example.countriesmvvm.BuildConfig
import com.example.countriesmvvm.domain.models.Continent

@Database(entities = [Continent::class], version = 1)
@TypeConverters(ContinentsDataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao() : AppDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    BuildConfig.APPLICATION_ID
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}