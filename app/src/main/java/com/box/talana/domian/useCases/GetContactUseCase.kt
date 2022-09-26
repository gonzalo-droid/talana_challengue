package com.box.talana.domian.useCases

import com.box.talana.domian.model.Contact
import com.box.talana.domian.model.Feed
import com.box.talana.domian.repository.TalanaRepository
import javax.inject.Inject


class GetContactUseCase
@Inject constructor(private val talanaRepository: TalanaRepository) :
    BaseUseCase<Contact, GetContactUseCase.Params>() {

    override suspend fun run(params: Params) = talanaRepository.contactId(params.contactId)

    data class Params(val contactId: Int)
}
