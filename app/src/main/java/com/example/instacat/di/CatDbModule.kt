package com.example.instacat.di

import android.content.Context
import androidx.room.Room
import com.example.android.devbyteviewer.database.CatDao
import com.example.android.devbyteviewer.database.CatDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CatDbModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideCatDatabase(context: Context): CatDatabase {
        return Room.databaseBuilder(
            context,
            CatDatabase::class.java,
            "cat"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao {
        return catDatabase.catDao()
    }
}