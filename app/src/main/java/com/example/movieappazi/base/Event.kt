package com.example.movieappazi.base

class Event<T>(
    private val value: T
) {
    private var handled: Boolean = false

    fun getValue(): T? {
        if (handled) return null
        handled = true
        return value
    }
}