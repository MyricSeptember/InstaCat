package com.example.instacat.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * [MapKey] that can be used to bind view models by type.
 */
@MapKey
@Retention(AnnotationRetention.BINARY)
annotation class ViewModelKey(val value: KClass<out ViewModel>)