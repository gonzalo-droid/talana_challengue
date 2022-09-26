package com.box.talana.presentation.features.home

import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.base.UiEffect


sealed class HomeEffect : UiEffect {

    object ShowSuccess : HomeEffect()

    data class ShowMessageFailure constructor(val failure: Failure) : HomeEffect()
}