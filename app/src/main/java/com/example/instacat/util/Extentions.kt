package com.example.cattestproject.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.instacat.ui.detail.ExoUtilHandler
import com.example.instacat.util.ExoUtil

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun Fragment.lifecycleAwareHandler(lifecycleOwner: LifecycleOwner, exoUtil: ExoUtil) {
    val observer = ExoUtilHandler(exoUtil)
    lifecycleOwner.lifecycle.removeObserver(observer)
    lifecycleOwner.lifecycle.addObserver(observer)
}