package com.box.talana.presentation.features.auth

import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.base.UiEffect


sealed class LoginEffect : UiEffect {

    object ShowSuccess : LoginEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : LoginEffect()
}