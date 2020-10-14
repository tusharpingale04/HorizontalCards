package com.tushar.horizontalcards.di

import com.tushar.horizontalcards.BuildConfig
import com.tushar.horizontalcards.network.CoursesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder() : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideApiService(builder: Retrofit.Builder) : CoursesApiService{
        return builder.build().create(CoursesApiService::class.java)
    }
}