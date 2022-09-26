package com.box.talana.presentation.features.detail


object DetailReducer {

    private lateinit var viewState: DetailViewState

    fun instance(viewState: DetailViewState) {
        this.viewState = viewState
    }

    fun selectState(state: DetailState) {
        when (state) {
            is DetailState.Idle -> {}
            is DetailState.Loading -> viewState.loading()
            is DetailState.ContactSuccess -> {
                viewState.contact(data = state.data)
            }

        }
    }

    fun selectEffect(effect: DetailEffect) {
        when (effect) {
            is DetailEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
        }
    }
}