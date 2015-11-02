package com.github.chuross.qiiip.domain.core

interface Identity<V> : ValueObject {

    fun getValue(): V
}