package com.box.talana.presentation.features.home


object HomeReducer {

    private lateinit var viewState: HomeViewState

    fun instance(viewState: HomeViewState){
        this.viewState = viewState
    }

    fun selectState(state: HomeState) {
        when (state) {
            is HomeState.Idle -> {}
            is HomeState.Loading -> viewState.loading()
            is HomeState.ListFeed -> {
                viewState.listFeed(state.data)
            }

        }
    }

    fun selectEffect(effect: HomeEffect) {
        when (effect) {
            is HomeEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            else -> {}
        }
    }
}