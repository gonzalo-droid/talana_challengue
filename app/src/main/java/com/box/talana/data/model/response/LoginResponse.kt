package com.box.talana.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("STATUS")  val status: String,
    @SerializedName("api-token")  val token: String
) : Serializable

