package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.Identity

data class TagIdentity(override val value: String) : Identity<String>