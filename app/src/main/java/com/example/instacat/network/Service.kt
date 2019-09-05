package com.example.cattestproject.network

import retrofit2.http.GET

interface CatApiService {
    @GET("images/get?format=json&results_per_page=100&size=small&type=png")
    suspend fun getCats(): List<NetworkCat>
}

