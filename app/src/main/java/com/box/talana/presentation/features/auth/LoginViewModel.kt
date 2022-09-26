package com.box.talana.presentation.features.auth

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.useCases.BaseUseCase
import com.box.talana.domian.useCases.DoLoginUseCase
import com.box.talana.domian.useCases.GetInitAppUseCase
import com.box.talana.presentation.base.BaseViewModel
import com.box.talana.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
    private val getInitAppUseCase: GetInitAppUseCase
) : BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

    val user = User()

    override fun createInitialState(): LoginState {
        return LoginState.Idle
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.LoginClicked -> verifyLogin()
            LoginEvent.VerifyToken -> getInitApp()
        }
    }


    private fun getInitApp() = getInitAppUseCase( BaseUseCase.None()) {
        it.either(::handleFailure, ::handleVerifyToken)
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
        setEffect { LoginEffect.GoToHome }
    }

    private fun handleVerifyToken(success: Boolean) {
        if(success){
            setEffect { LoginEffect.GoToHome }
        }
    }


    companion object Events {
        val loginClicked = LoginEvent.LoginClicked
    }
}