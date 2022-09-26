package com.box.talana.data.repository

import com.box.talana.R
import com.box.talana.core.funtional.Either.*
import com.box.talana.core.funtional.Either
import com.box.talana.core.funtional.Failure
import com.box.talana.data.mapper.TalanaMapper
import com.box.talana.data.model.request.LoginRequest
import com.box.talana.data.model.response.LoginResponse
import com.box.talana.data.service.TalanaService
import com.box.talana.domian.model.Contact
import com.box.talana.domian.model.Feed
import com.box.talana.domian.repository.TalanaRepository
import javax.inject.Inject

class TalanaRepositoryImpl
@Inject constructor(
    private val service: TalanaService,
    private val mapper: TalanaMapper
) : TalanaRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Either<Failure, Boolean> {
        return when (val response =
            service.login(LoginRequest(username = email, password = password))) {
            is Right -> {
                val loginResponse: LoginResponse = response.b

                return if (loginResponse.token.isNotEmpty()) {

                    Right(true)
                } else {
                    Left(Failure.DefaultError(R.string.error_user_message))
                }
            }
            is Left -> Left(Failure.DefaultError(R.string.error_credentials))
        }
    }

    override suspend fun feed(): Either<Failure, List<Feed>> {
        return when (val response = service.feed()) {
            is Right -> {
               Right(mapper.feedToDomain(response.b))
            }
            is Left ->Left(response.a)
        }
    }

    override suspend fun contactId(contactId: Int): Either<Failure, Contact> {
        return when (val response = service.contactId (
            contactId = contactId,
        )) {
            is Right -> {
                Right(mapper.contactToDomain(response.b))
            }
            is Left -> Left(response.a)
        }
    }
}