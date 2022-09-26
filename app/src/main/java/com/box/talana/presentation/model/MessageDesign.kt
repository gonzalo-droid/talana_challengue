package com.box.talana.presentation.model

import com.box.talana.R


class MessageDesign private constructor(
    val idMessage: Int = R.string.error_user_message,
) {
    data class Builder(
        var idMessage: Int = R.string.error_user_message,
    ) {
        fun idMessage(idMessage: Int) = apply { this.idMessage = idMessage }
        fun build() = MessageDesign(idMessage)
    }
}
