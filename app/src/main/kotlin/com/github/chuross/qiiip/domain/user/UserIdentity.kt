package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.Identity

data class UserIdentity(override val value: String) : Identity<String>