package com.box.talana.data.mapper


import com.box.talana.data.model.response.FeedResponse
import com.box.talana.domian.model.Feed
import javax.inject.Singleton

@Singleton
class TalanaMapperImpl : TalanaMapper {

    override suspend fun feedToDomain(data: List<FeedResponse>): List<Feed> {
        return data.map {
            Feed(
                id = it.id,
                title = it.title,
                image = it.image,
                date = it.date,
                description = it.description,
                published = it.published,
                authorId = it.authorId
            )
        }
    }
}