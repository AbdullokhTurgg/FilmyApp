package com.example.movieappazi.app.utils

/** Throws an [IllegalStateException] with binding lifecycle error message. */
inline fun bindingLifecycleError(): Nothing = throw IllegalStateException(
    "Это свойство допустимо только между onCreateView и onDestroyView."
)