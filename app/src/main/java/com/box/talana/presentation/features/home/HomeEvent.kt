package com.box.talana.presentation.features.home

import com.box.talana.presentation.base.UiEvent

sealed class HomeEvent : UiEvent {


    object GetFeedClick : HomeEvent()

    object ShowFeedClick : HomeEvent()

    object LogoutClick : HomeEvent()


}