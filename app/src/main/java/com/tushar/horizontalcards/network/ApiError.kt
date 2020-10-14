package com.tushar.horizontalcards.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * This class wraps the error response of all APIs
 */
data class ApiError(
    @SerializedName(JsonKeys.KEY_STATUS_CODE)
    @Expose
    val errorCode: String? = "",
    @SerializedName(JsonKeys.KEY_STATUS_MESSAGE)
    @Expose
    val errorMessage: String? = ""
)