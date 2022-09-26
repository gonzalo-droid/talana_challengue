package com.box.talana.domian.model

import java.io.Serializable

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String
) : Serializable

