package com.box.talana.presentation.features.auth

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.useCases.DoLoginUseCase
import com.box.talana.presentation.base.BaseViewModel
import com.box.talana.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    val user = User()

    override fun createInitialState(): LoginState {
        return LoginState.Idle
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LoginClicked -> verifyLogin()
            LoginEvent.RecoveredPasswordClicked -> goRecoveryPassword()
        }
    }

    private fun goRecoveryPassword() {

    }

    private fun verifyLogin() {

        user.verifyLogin().let {

            if (it.first) {

                doLogin()

            } else {
                setEffect { LoginEffect.ShowMessageFailure(failure = it.second!!) }
            }
        }

    }


    private fun doLogin() {

        setState { LoginState.Loading }

        doLoginUseCase(
            DoLoginUseCase.Params(
                email = user.email.trim(), password = user.password.trim()
            )
        ) {
            it.either(::handleFailure, ::handleLogin)
        }

    }


    private fun handleFailure(failure: Failure) {
        setEffect { LoginEffect.ShowMessageFailure(failure = failure) }

    }

    private fun handleLogin(success: Boolean) {
        setState { LoginState.LoginSuccess(success = success) }
    }

    companion object Events {
        val loginClicked = LoginEvent.LoginClicked
    }
}