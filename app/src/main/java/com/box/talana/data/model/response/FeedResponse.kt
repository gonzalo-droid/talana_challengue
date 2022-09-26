package com.box.talana.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedResponse(
    @SerializedName("id")  val id: Int,
    @SerializedName("title")  val title: String,
    @SerializedName("image")  val image: String,
    @SerializedName("date")  val date: String,
    @SerializedName("description")  val description: String,
    @SerializedName("published")  val published: String,
    @SerializedName("author_id")  val authorId: String,
) : Serializable
