package com.box.talana.domian.useCases

import com.box.talana.domian.repository.TalanaRepository
import javax.inject.Inject

class DoLogoutUseCase
@Inject constructor(private val talanaRepository: TalanaRepository) : BaseUseCase<Boolean, BaseUseCase.None>() {

    override suspend fun run(params: None) = talanaRepository.logout()


}