package com.tushar.horizontalcards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tushar.horizontalcards.network.CoursesApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNull
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class CoursesTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: CoursesApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CoursesApiService::class.java)
    }

    @Test
    fun getCoursesListTest() = runBlocking {
        enqueueResponse("courses.json")
        val coursesData = service.getPopularCourses(isPremium = true, includeIndividual = true).body()?.data

        Assert.assertThat(coursesData, IsNull.notNullValue())
        Assert.assertThat(coursesData?.classes?.size, CoreMatchers.`is`(5))
        Assert.assertThat(coursesData?.classes?.get(0)?.title, CoreMatchers.`is`("RRB NTPC Live Coaching 3.0"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

}