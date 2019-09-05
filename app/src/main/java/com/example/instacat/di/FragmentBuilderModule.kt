package com.example.instacat.di

import com.example.cattestproject.overview.OverViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeOverviewFragment(): OverViewFragment

}