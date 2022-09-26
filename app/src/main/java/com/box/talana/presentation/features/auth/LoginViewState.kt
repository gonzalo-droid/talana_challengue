package com.box.talana.presentation.features.auth

import com.box.talana.core.funtional.Failure

interface LoginViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun homeActivity()

}