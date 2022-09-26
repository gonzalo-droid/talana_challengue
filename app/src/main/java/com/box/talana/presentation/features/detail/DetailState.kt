package com.box.talana.presentation.features.detail

import com.box.talana.domian.model.Contact
import com.box.talana.presentation.base.UiState

sealed class DetailState : UiState {

    object Idle : DetailState()
    object Loading : DetailState()
    data class ContactSuccess constructor(val data: Contact) : DetailState()

}