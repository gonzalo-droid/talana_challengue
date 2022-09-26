package com.box.talana.presentation.features.auth

import android.os.Bundle
import androidx.activity.viewModels
import com.box.talana.BR
import com.box.talana.core.extension.dataBinding
import com.box.talana.core.extension.lifecycleScopeCreate
import com.box.talana.core.extension.startNewActivity
import com.box.talana.core.funtional.Failure
import com.box.talana.databinding.ActivityLoginBinding
import com.box.talana.presentation.base.BaseActivityWithViewModel
import com.box.talana.presentation.features.home.HomeActivity
import com.box.talana.presentation.model.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivityWithViewModel<ActivityLoginBinding, LoginViewModel>(),
    LoginViewState {

    override val binding: ActivityLoginBinding by dataBinding(ActivityLoginBinding::inflate)

    override val viewModel: LoginViewModel by viewModels()

    override val dataBindingViewModel = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginReducer.instance(viewState = this)
        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.apply {
            lifecycleScopeCreate(activity = this@LoginActivity, method = {
                state.collect { state ->
                    LoginReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@LoginActivity, method = {
                effect.collect { effect ->
                    LoginReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun messageFailure(failure: Failure) {
        closeLoadingDialog()
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))

    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun homeActivity() {
        closeLoadingDialog()
        startNewActivity<HomeActivity>()
        finish()
    }

}