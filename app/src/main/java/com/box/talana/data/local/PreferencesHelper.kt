package com.box.talana.data.local

import android.content.SharedPreferences
import javax.inject.Inject


class PreferencesHelper @Inject constructor(

    private val sharedPreferences: SharedPreferences

) {

     var sp : SharedPreferences

    init {
        sp = initPreference()
    }

    private fun initPreference(): SharedPreferences = sharedPreferences


    companion object {

        val USER_TOKEN = "USER_TOKEN"

        private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
            val editMe = edit()
            operation(editMe)
            editMe.apply()
        }

        var SharedPreferences.userToken
            get() = getString(USER_TOKEN, "")
            set(value) {
                editMe {
                    it.putString(USER_TOKEN, value)
                }
            }

        var SharedPreferences.clearValues
            get() = { }
            set(value) {
                editMe {
                    it.clear()
                }
            }


    }

}