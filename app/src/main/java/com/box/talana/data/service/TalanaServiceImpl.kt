package com.box.talana.data.service

import com.box.talana.core.funtional.Either
import com.box.talana.core.funtional.Failure
import com.box.talana.data.model.request.LoginRequest
import com.box.talana.data.model.response.FeedResponse
import com.box.talana.data.model.response.LoginResponse
import com.box.talana.data.network.NetworkHandler
import com.box.talana.data.remote.TalanaApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TalanaServiceImpl
@Inject constructor(
    private val api: TalanaApi,
    private val networkHandler: NetworkHandler
) : TalanaService {

    override suspend fun login(body: LoginRequest): Either<Failure, LoginResponse> {
        return networkHandler.callService { api.login(body) }
    }

    override suspend fun feed(): Either<Failure, List<FeedResponse>> {
        return networkHandler.callService { api.feed() }
    }

}