package com.box.talana.presentation.features.auth


object LoginReducer {

    private lateinit var viewState: LoginViewState

    fun instance(viewState: LoginViewState){
        this.viewState = viewState
    }

    fun selectState(state: LoginState) {
        when (state) {
            is LoginState.Idle -> {}
            is LoginState.Loading -> viewState.loading()
        }
    }

    fun selectEffect(effect: LoginEffect) {
        when (effect) {
            is LoginEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is LoginEffect.GoToHome -> viewState.homeActivity()
        }
    }
}