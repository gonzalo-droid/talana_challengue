package com.box.talana.presentation.util

import android.util.Log
import com.box.talana.R
import com.box.talana.core.funtional.Failure
import com.box.talana.presentation.model.MessageDesign

class BindingUtil {

    companion object {

        fun reducerFailure(failure: Failure): MessageDesign {
            return when (failure) {
                is Failure.UnauthorizedOrForbidden -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .build()

                is Failure.None -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .build()

                is Failure.NetworkConnectionLostSuddenly -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .build()

                is Failure.NoNetworkDetected -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .build()

                is Failure.SSLError -> MessageDesign.Builder()
                    .idMessage(R.string.error_no_internet)
                    .build()

                is Failure.TimeOut -> {
                    MessageDesign.Builder()
                        .idMessage(R.string.error_server)
                        .build()
                }

                is Failure.ServerBodyError -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .build()

                is Failure.DataToDomainMapperFailure -> MessageDesign.Builder()
                    .idMessage(R.string.error_user_message)
                    .build()

                is Failure.ServiceUncaughtFailure -> {
                    Log.d("Error serve", failure.uncaughtFailureMessage)
                    MessageDesign.Builder()
                        .idMessage(R.string.error_server)
                        .build()
                }

                is Failure.DefaultError -> MessageDesign.Builder()
                    .idMessage(failure.idMessage)
                    .build()
            }
        }
    }
}