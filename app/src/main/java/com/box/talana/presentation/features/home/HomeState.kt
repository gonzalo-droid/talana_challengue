package com.box.talana.presentation.features.home

import com.box.talana.domian.model.Feed
import com.box.talana.presentation.base.UiState

sealed class HomeState : UiState {

    object Idle : HomeState()
    object Loading : HomeState()
    data class ListFeed constructor(val data: List<Feed>) : HomeState()

}