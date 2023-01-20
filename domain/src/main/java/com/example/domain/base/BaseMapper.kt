package com.example.domain.base

interface BaseMapper<From, To> {
    fun map(from: From): To
}