package com.box.talana.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.model.MessageDesign
import com.box.talana.presentation.util.BindingUtil


abstract class BaseFragmentWithViewModel<ViewDataBindingClass : ViewDataBinding, ViewModelType : ViewModel> :
    BaseFragment<ViewDataBindingClass>() {
    protected abstract val viewModel: ViewModelType
    protected abstract val dataBindingViewModel: Int
    private lateinit var rootView: View

    //Shows, hide, error message view.
    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause: LiveData<Boolean>
        get() = _showErrorCause

    // The resource default value of the error or any error(Exception, server side, etc).
    private val _errorCause = MutableLiveData<Any>()
    val errorCause: LiveData<Any>
        get() = _errorCause

    private fun init() {
        // Binding the viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = binding.root
        binding.setVariable(dataBindingViewModel, viewModel)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.executePendingBindings()
        onViewIsCreated(view)
    }

    open fun onViewIsCreated(view: View) {}


    open fun getUseCaseFailureFromBase(failure: Failure) : MessageDesign {

        return BindingUtil.reducerFailure(failure)
    }

}
