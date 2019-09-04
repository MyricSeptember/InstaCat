package com.example.cattestproject.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.devbyteviewer.domain.Cat

class DetailViewModel(cat: Cat, app: Application) : AndroidViewModel(app) {
    private val _selectedCat = MutableLiveData<Cat>()

    val selectedcat: LiveData<Cat>
        get() = _selectedCat

    init {
        _selectedCat.value = cat
    }
}