package com.box.talana.data.mapper

import com.box.talana.data.model.response.ContactResponse
import com.box.talana.data.model.response.FeedResponse
import com.box.talana.domian.model.Contact
import com.box.talana.domian.model.Feed


interface TalanaMapper {

    suspend fun feedToDomain(data: List<FeedResponse>): List<Feed>

    suspend fun contactToDomain(data: ContactResponse): Contact


}