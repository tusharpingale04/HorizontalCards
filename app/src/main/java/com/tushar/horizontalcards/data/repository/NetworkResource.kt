package com.tushar.horizontalcards.data.repository

import androidx.annotation.MainThread
import com.google.gson.Gson
import com.tushar.horizontalcards.AppController
import com.tushar.horizontalcards.R
import com.tushar.horizontalcards.network.ApiError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
abstract class NetworkResource<T> {

    fun asFlow() = flow<Resource<T>> {

        // Emit Loading State
        emit(Resource.loading(null))

        try {
            // Fetch latest data from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val body = apiResponse.body()

            // Check for response validation
            if (apiResponse.isSuccessful && body != null) {
                emit(Resource.success(body))
            } else {
                // Something went wrong! Emit Error state with message.
                val apiError: ApiError = Gson().fromJson(apiResponse.errorBody()?.charStream(), ApiError::class.java)
                val errorMsg = if (!apiError.errorMessage.isNullOrEmpty()) {
                    apiError.errorMessage
                } else{
                    AppController.getInstance().getString(R.string.error_server)
                }
                emit(Resource.error(errorMsg, null))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(Resource.error(getErrorMessage(e), null))
            e.printStackTrace()
        }
    }

    /** 
     * @param throwable class to check instance of when exception occurred 
     */
    private fun getErrorMessage(throwable: Throwable): String {
        return when {
            throwable is SocketTimeoutException -> {
                AppController.getInstance().getString(R.string.error_network_timeout)
            }
            throwable.fillInStackTrace() is UnknownHostException -> {
                AppController.getInstance().getString(R.string.error_no_connection)
            }
            else -> {
               AppController.getInstance().getString(R.string.error_server)
            }
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<T>
}