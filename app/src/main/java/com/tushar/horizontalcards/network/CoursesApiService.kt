package com.tushar.horizontalcards.network

import com.tushar.horizontalcards.data.remote.CoursesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This Interface consists of all the abstract methods pertaining to network present inside the app.
 */
interface CoursesApiService {

    /** 
     * @param isPremium true to fetch premium courses, false otherwise 
     * @param includeIndividual true to fetch individual courses, false otherwise 
     */
    @GET("popular-courses")
    suspend fun getPopularCourses(
        @Query(JsonKeys.KEY_IS_PREMIUM) isPremium: Boolean,
        @Query(JsonKeys.KEY_INCLUDE_INDIVIDUAL) includeIndividual: Boolean
    ): Response<CoursesResponseModel>

}