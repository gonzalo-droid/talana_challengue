package com.box.talana.presentation.features.detail

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.model.Contact
import com.box.talana.domian.useCases.GetContactUseCase
import com.box.talana.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val getContactUseCase: GetContactUseCase
) : BaseViewModel<DetailEvent, DetailState, DetailEffect>() {

    var contactId = ""

    override fun createInitialState(): DetailState {
        return DetailState.Idle
    }

    override fun handleEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.AuthorClick -> contact()
        }
    }

    private fun contact() {
        getContactUseCase(
            GetContactUseCase.Params(
                contactId = contactId.toInt()
            )
        ) {
            it.either(::handleFailure, ::handleContact)
        }
    }


    private fun handleFailure(failure: Failure) {
        setEffect { DetailEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleContact(data: Contact) {
        setState { DetailState.ContactSuccess(data = data) }
    }

}