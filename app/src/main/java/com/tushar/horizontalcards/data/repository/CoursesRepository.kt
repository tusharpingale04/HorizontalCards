package com.tushar.horizontalcards.data.repository

import com.tushar.horizontalcards.data.remote.CoursesResponseModel
import com.tushar.horizontalcards.network.CoursesApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/** 
 * Repository for Courses 
 * @param apiService implementation for Course Api Service 
 */
@ExperimentalCoroutinesApi
class CoursesRepository @Inject constructor(
    val apiService: CoursesApiService
) {

    fun getCourses(
        isPremium: Boolean,
        includeIndividual: Boolean
    ): Flow<Resource<CoursesResponseModel>> {
        return object : NetworkResource<CoursesResponseModel>() {
            override suspend fun fetchFromRemote(): Response<CoursesResponseModel> {
                return apiService.getPopularCourses(isPremium, includeIndividual)
            }
        }.asFlow()
    }

}