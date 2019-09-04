package com.example.cattestproject.repository

import androidx.lifecycle.LiveData
import com.example.android.devbyteviewer.database.CatDatabase
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.Cat
import com.example.cattestproject.network.CatsApi
import com.example.cattestproject.network.asDatabaseModel
import com.example.cattestproject.util.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatsRepository(private val database: CatDatabase) {

    val cats: LiveData<List<Cat>> = database.catDao.getCats().map { it.asDomainModel() }

    suspend fun fetchCatsData() {
        withContext(Dispatchers.IO) {
            val catslist = CatsApi.retrofitService.getCats()
            database.catDao.insertAll(*catslist.asDatabaseModel())
        }
    }
}