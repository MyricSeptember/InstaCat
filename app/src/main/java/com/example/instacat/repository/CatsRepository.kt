package com.example.cattestproject.repository

import androidx.lifecycle.LiveData
import com.example.android.devbyteviewer.database.CatDao
import com.example.android.devbyteviewer.database.asDomainModel
import com.example.android.devbyteviewer.domain.Cat
import com.example.cattestproject.network.CatApiService
import com.example.cattestproject.network.asDatabaseModel
import com.example.cattestproject.util.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatsRepository @Inject constructor(
    private val catDao: CatDao, private val catService: CatApiService
) {
    val cats: LiveData<List<Cat>> = catDao.getCats().map { it.asDomainModel() }

    suspend fun fetchCatsData() {
        withContext(Dispatchers.IO) {
            val catslist = catService.getCats()
            catDao.insertAll(*catslist.asDatabaseModel())
        }
    }
}