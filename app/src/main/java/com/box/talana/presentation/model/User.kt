package com.box.talana.presentation.model

import com.box.talana.R
import com.box.talana.core.funtional.Failure
import java.io.Serializable


open class User constructor(
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastName: String = "",
    var phone: String = "",
) : Serializable {

    fun verifyLogin(): Pair<Boolean, Failure?> {

        return when {

            isEmailEmpty() -> {

                Pair(
                    false,
                    Failure.DefaultError(R.string.email_fail)
                )
            }
            isPasswordEmpty() -> {

                Pair(
                    false,
                    Failure.DefaultError(R.string.password_fail)
                )
            }
            else -> {

                Pair(
                    true,
                    null
                )
            }
        }
    }

    private fun isEmailEmpty(): Boolean {

        return email.isEmpty()
    }

    private fun isPasswordEmpty(): Boolean {

        return password.isEmpty()
    }

}
