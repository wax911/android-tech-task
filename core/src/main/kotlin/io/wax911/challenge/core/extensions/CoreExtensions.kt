package io.wax911.challenge.core.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.jvm.Throws

/**
 * Extension for getting system services
 */
inline fun <reified T> Context.systemServiceOf(): T? =
    ContextCompat.getSystemService(this, T::class.java)

@Throws(IllegalArgumentException::class)
fun Context.layoutInflater(): LayoutInflater =
    requireNotNull(systemServiceOf<LayoutInflater>())

fun View.layoutInflater(): LayoutInflater =
    context.layoutInflater()