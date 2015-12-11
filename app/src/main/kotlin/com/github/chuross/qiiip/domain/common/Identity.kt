package com.github.chuross.qiiip.domain.common

interface Identity<V> : ValueObject {

    fun getValue(): V
}