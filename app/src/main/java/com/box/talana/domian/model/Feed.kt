package com.box.talana.domian.model

import java.io.Serializable

data class Feed(
    val id: Int,
    val title: String,
    val image: String,
    val date: String,
    val description: String,
    val published: String,
    val authorId: String,
) : Serializable
