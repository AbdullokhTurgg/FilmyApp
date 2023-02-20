package com.example.ui_core.extensions.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater

fun Context.layoutInflater(): LayoutInflater = LayoutInflater.from(this)

@SuppressLint("InternalInsetResource", "DiscouragedApi")
fun Context.getStatusBarHeight(): Int =
    this.resources
        .getIdentifier("status_bar_height", "dimen", "android")
        .takeIf { resourceId -> resourceId > 0 }
        ?.let { resourceId -> this.resources.getDimensionPixelSize(resourceId) }
        ?: 0