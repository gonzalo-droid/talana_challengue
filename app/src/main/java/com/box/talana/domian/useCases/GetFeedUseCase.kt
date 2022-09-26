package com.box.talana.domian.useCases

import com.box.talana.domian.model.Feed
import com.box.talana.domian.repository.TalanaRepository
import javax.inject.Inject


class GetFeedUseCase
@Inject constructor(private val talanaRepository: TalanaRepository)
    : BaseUseCase<List<Feed>, BaseUseCase.None>() {

    override suspend fun run(params: None) = talanaRepository.feed()

}
