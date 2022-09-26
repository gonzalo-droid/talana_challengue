package com.box.talana.domian.repository

import com.box.talana.core.funtional.Either
import com.box.talana.core.funtional.Failure
import com.box.talana.domian.model.Contact
import com.box.talana.domian.model.Feed


interface TalanaRepository {

    suspend fun login(
        email: String,
        password: String,
    ): Either<Failure, Boolean>

    suspend fun feed(): Either<Failure, List<Feed>>

    suspend fun verifyToken(): Either<Failure, Boolean>

    suspend fun logout(): Either<Failure, Boolean>

    suspend fun contactId(contactId: Int): Either<Failure, Contact>
}