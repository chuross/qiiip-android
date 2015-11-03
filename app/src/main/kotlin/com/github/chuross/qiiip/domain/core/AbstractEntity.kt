package com.github.chuross.qiiip.domain.core

import org.apache.commons.lang3.SerializationUtils

abstract class AbstractEntity<I : Identity<*>, E : Entity<I, E>>(identity: I) : Entity<I, E> {

    private val identity = identity

    override fun getIdentity(): I = identity

    override fun sameIdentity(entity: E): Boolean = identity.equals(entity.getIdentity())

    override fun equals(that: E): Boolean = this == that || (that is E && sameIdentity(that))

    @Suppress("UNCHECKED_CAST")
    override fun clone(): E = SerializationUtils.clone(this) as E
}