package com.example.instacat.di

import android.content.Context
import com.example.android.devbyteviewer.database.CatDao
import com.example.cattestproject.network.CatApiService
import com.example.cattestproject.repository.CatsRepository
import com.example.instacat.CatsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * AppModule shall be used initialize objects used across our application,
 * such as Room(Database), Retrofit, Shared Preference, etc
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: CatsApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideCatsRepository(databaseCat: CatDao, catService: CatApiService): CatsRepository =
        CatsRepository(databaseCat, catService)


//    @Singleton
//    @Provides
//    fun providesRoomDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)
}