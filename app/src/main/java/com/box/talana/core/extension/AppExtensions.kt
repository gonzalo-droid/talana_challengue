package com.box.talana.core.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.box.talana.domian.model.Feed
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun Date.dateFormat(format: String): String {

    return SimpleDateFormat(format).format(this)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun String.dateFormat(format: String): Date {

    val dateFormat = SimpleDateFormat(format)

    return dateFormat.parse(this)
}

fun String.html() : Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }
}


@Suppress("DEPRECATION")
infix fun String.putFeed(activity: Activity) =
    activity.intent.extras!!.getSerializable(this) as Feed


fun lifecycleScopeCreate(activity: Activity, method: suspend () -> Unit) =
    (activity as AppCompatActivity).lifecycleScope.launch {

        /**
         * Api implements repeatOnLifecycle
         *
         * source: https://medium.com/androiddevelopers/repeatonlifecycle-api-design-story-8670d1a7d333
         */
        activity.repeatOnLifecycle(Lifecycle.State.CREATED) {

            method()
        }

    }
