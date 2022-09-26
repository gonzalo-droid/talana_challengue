package com.box.talana.presentation.features.detail

import com.box.talana.presentation.base.UiEvent

sealed class DetailEvent : UiEvent {

    object AuthorClick : DetailEvent()

}