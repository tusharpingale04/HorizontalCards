package com.tushar.horizontalcards.di

import com.tushar.horizontalcards.ui.courses.CourseAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @CoursesScope
    fun provideCoursesAdapter() = CourseAdapter()

}