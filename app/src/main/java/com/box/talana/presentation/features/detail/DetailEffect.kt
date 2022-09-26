package com.box.talana.presentation.features.detail

import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.base.UiEffect


sealed class DetailEffect : UiEffect {

    data class ShowMessageFailure constructor(val failure: Failure) : DetailEffect()
}