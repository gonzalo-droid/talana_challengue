package com.box.talana.domian.useCases

import com.box.talana.domian.repository.TalanaRepository
import javax.inject.Inject


class DoLoginUseCase
@Inject constructor(private val talanaRepository: TalanaRepository) : BaseUseCase<Boolean, DoLoginUseCase.Params>() {

    override suspend fun run(params: Params) = talanaRepository.login(params.email, params.password)

    data class Params(val email: String, val password: String)
}
