package com.box.talana.presentation.features.home

import com.box.talana.core.funtional.Failure
import com.box.talana.domian.model.Feed

interface HomeViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun logout()


    fun listFeed(data : List<Feed>)

}