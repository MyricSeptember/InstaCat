package com.example.instacat.di

import com.example.cattestproject.overview.OverViewFragment
import com.example.instacat.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}