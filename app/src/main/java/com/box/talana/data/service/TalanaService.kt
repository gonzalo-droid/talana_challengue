package com.box.talana.data.service

import com.box.talana.core.funtional.Either
import com.box.talana.core.funtional.Failure
import com.box.talana.data.model.request.LoginRequest
import com.box.talana.data.model.response.FeedResponse
import com.box.talana.data.model.response.LoginResponse


interface TalanaService {

    suspend fun login(body: LoginRequest): Either<Failure, LoginResponse>

    suspend fun feed(): Either<Failure, List<FeedResponse>>


}