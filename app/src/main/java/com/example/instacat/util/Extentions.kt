package com.example.cattestproject.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}
