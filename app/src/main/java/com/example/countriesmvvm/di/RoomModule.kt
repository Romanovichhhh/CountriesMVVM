package com.example.countriesmvvm.di

import android.content.Context
import com.example.countriesmvvm.data.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Provides
    fun provideAppDao(db : AppDatabase) = db.appDao()
}