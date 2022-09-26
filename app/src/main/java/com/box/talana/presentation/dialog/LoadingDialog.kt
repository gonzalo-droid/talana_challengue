package com.box.talana.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.box.talana.R
import com.box.talana.databinding.DialogFragmentLoadingBinding


class LoadingDialog(var loadingText: String? = null) : DialogFragment() {

    lateinit var binding: DialogFragmentLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentLoadingBinding.inflate(layoutInflater)

        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvProgressText.text = loadingText ?: getString(R.string.label_loading)
    }

    fun updateLoadingText(someText: String?) {
        if (someText != null) {
            binding.tvProgressText.text = someText
        }
    }
}
