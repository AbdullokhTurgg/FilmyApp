package com.example.data.data.models

import android.content.Context
import androidx.annotation.StringRes


/** Обертка вокруг строкового сообщения. Для получения строкового сообщения вызовите метод [format]. */
sealed class ResourceString {
    /** Возвращает форматированное строковое сообщение. */
    abstract fun format(context: Context): String
}

/** Оболочка для сообщения из строкового ресурса. */
data class IdResourceString(@StringRes val id: Int) : ResourceString() {
    override fun format(context: Context): String = context.getString(id)
}