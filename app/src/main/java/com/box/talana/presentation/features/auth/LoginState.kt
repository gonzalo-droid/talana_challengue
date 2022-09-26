package com.box.talana.presentation.features.auth

import com.box.talana.presentation.base.UiState

sealed class LoginState : UiState {

    object Idle : LoginState()
    object Loading : LoginState()
}