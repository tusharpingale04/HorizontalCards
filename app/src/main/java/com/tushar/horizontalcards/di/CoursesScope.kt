package com.tushar.horizontalcards.di

import javax.inject.Qualifier

/**
 * This annotation class provides scoping for Course module wise injection
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoursesScope