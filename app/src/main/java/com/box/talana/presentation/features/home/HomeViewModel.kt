package com.box.talana.presentation.features.home

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.model.Feed
import com.box.talana.domian.useCases.BaseUseCase
import com.box.talana.domian.useCases.DoLoginUseCase
import com.box.talana.domian.useCases.GetFeedUseCase
import com.box.talana.presentation.base.BaseViewModel
import com.box.talana.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getFeedUseCase: GetFeedUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    override fun createInitialState(): HomeState {
        return HomeState.Idle
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.GetFeedClick -> lisFeed()
            HomeEvent.ShowFeedClick -> {}
        }
    }

    private fun lisFeed() {
        setState { HomeState.Loading }

        getFeedUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleFeed)
        }
    }


    private fun handleFailure(failure: Failure) {
        setEffect { HomeEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleFeed(data: List<Feed>) {
        setState { HomeState.ListFeed(data = data) }
    }

    companion object Events {

    }
}