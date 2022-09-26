package com.box.talana.data.remote

import com.box.talana.data.model.request.LoginRequest
import com.box.talana.data.model.response.ContactResponse
import com.box.talana.data.model.response.FeedResponse
import com.box.talana.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface TalanaApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET("feed")
    suspend fun feed(
    ): Response<List<FeedResponse>>

    @GET("contact/{author_id}")
    suspend fun contactById(
        @Path("author_id") authorId: Int,
    ): Response<ContactResponse>

}