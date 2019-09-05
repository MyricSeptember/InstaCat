package com.example.instacat.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Checks if a network connection exists.
 */
open
class NetworkUtils(val context: Context) {

    open fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}