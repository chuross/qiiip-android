package com.github.chuross.qiiip.domain.common

import java.io.Serializable

interface Entity<I : Identity<*>, E : Entity<I, E>> : Cloneable, Serializable {

    val identity: I

    fun sameIdentity(entity: E): Boolean

    fun equals(that: E): Boolean

    override fun clone(): E
}