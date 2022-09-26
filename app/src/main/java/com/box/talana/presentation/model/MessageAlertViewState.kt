package com.box.talana.presentation.model

import com.box.talana.core.funtional.Failure


interface MessageAlertViewState {

    fun messageFailure(failure: Failure)

}