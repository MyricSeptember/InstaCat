package com.example.android.devbyteviewer.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cat(
    val id: String,
    val title: String,
    val description: String,
    val url: String
) : Parcelable