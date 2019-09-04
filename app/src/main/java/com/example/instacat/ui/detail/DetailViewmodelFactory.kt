package com.example.cattestproject.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.devbyteviewer.domain.Cat

class DetailViewModelFactory(
    private val cat: Cat,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(cat, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}