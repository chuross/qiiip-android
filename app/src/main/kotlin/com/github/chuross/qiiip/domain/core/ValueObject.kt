package com.github.chuross.qiiip.domain.core

import java.io.Serializable

interface ValueObject : Serializable {

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}