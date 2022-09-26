package com.box.talana.presentation.features.auth

import com.box.talana.presentation.base.UiEvent

sealed class LoginEvent : UiEvent {

    object LoginClicked : LoginEvent()

}