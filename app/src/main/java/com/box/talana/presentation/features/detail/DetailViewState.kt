package com.box.talana.presentation.features.detail

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.model.Contact

interface DetailViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun contact(data: Contact)

}