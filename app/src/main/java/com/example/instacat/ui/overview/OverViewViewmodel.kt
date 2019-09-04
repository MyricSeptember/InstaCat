package com.example.cattestproject.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.android.devbyteviewer.database.getDatabase
import com.example.android.devbyteviewer.domain.Cat
import com.example.cattestproject.repository.CatsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

enum class CatsApiStatus { LOADING, ERROR, DONE }

class OverViewViewmodel(application: Application) : AndroidViewModel(application) {

    private val _navigateToSelectedCat = MutableLiveData<Cat>()

    val navigateToSelectedCat: LiveData<Cat>
        get() = _navigateToSelectedCat

    private val _status = MutableLiveData<CatsApiStatus>()

    val status: LiveData<CatsApiStatus>
        get() = _status

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val catsRepository = CatsRepository(database)

    init {
        viewModelScope.launch {
            catsRepository.fetchCatsData()
        }
    }

    val listResult = catsRepository.cats

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayCatDetails(cat: Cat) {
        _navigateToSelectedCat.value = cat
    }

    fun displayCatDetailsComplete() {
        _navigateToSelectedCat.value = null
    }

    fun setStatus(status: CatsApiStatus) {
        _status.value = status
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverViewViewmodel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverViewViewmodel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}