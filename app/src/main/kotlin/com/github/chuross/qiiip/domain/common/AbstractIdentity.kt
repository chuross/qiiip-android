package com.github.chuross.qiiip.domain.common

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

abstract class AbstractIdentity<V>(value: V) : Identity<V> {

    private val value = value

    override fun getValue(): V = value

    override fun equals(other: Any?): Boolean = EqualsBuilder.reflectionEquals(this, other)

    override fun hashCode(): Int = HashCodeBuilder.reflectionHashCode(this)
}