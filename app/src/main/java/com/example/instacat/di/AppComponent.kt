package com.example.instacat.di

import android.app.Application
import com.example.instacat.CatsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// This is what links all the dependencies from your modules together.
// Compilation will fail if you inject an object that is not provided anywhere.
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
       FragmentBuilderModule::class,
        ViewModelModule::class,
        AppModule::class,
        NetworkModule::class,
        CatDbModule::class
    ]
)
interface AppComponent : AndroidInjector<CatsApplication> {
    @Component.Builder
    interface Builder {
        // provide Application instance into DI
        @BindsInstance
        fun setApplication(application: Application): Builder

        fun build(): AppComponent
    }
}