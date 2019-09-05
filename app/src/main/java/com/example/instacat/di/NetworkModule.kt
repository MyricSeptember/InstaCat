package com.example.instacat.di

import com.example.cattestproject.network.CatApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
object NetworkModule {

    const val CAT_BASE_URL = "cat_base_url"
    private const val BASE_URL = "https://api.thecatapi.com/api/"

    @JvmStatic
    @Provides
    @Named(CAT_BASE_URL)
    fun provideBaseUrlString(): String {
        return BASE_URL
    }

    @Provides
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @JvmStatic
    fun provideCatService(moshi: Moshi, @Named(CAT_BASE_URL) baseUrl: String): CatApiService {

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .build()
            .create(CatApiService::class.java)
    }
}