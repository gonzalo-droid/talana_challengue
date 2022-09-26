package com.box.talana.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContactResponse(
    @SerializedName("id")  val id: Int,
    @SerializedName("firstName")  val firstName: String,
    @SerializedName("lastName")  val lastName: String,
    @SerializedName("gender")  val gender: String
) : Serializable

