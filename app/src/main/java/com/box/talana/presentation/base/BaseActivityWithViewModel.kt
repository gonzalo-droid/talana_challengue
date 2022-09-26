package com.box.talana.presentation.base
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.dialog.LoadingDialog
import com.box.talana.presentation.model.MessageDesign
import com.box.talana.presentation.util.BindingUtil

abstract class BaseActivityWithViewModel<VBinding : ViewDataBinding, ViewModelType : ViewModel> :
    BaseActivity<VBinding>() {

    protected abstract val viewModel: ViewModelType

    protected abstract val dataBindingViewModel: Int

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.setVariable(dataBindingViewModel, viewModel)
        binding.executePendingBindings()
    }

    open fun getUseCaseFailureFromBase(failure: Failure): MessageDesign {

        return BindingUtil.reducerFailure(failure)

    }

}
