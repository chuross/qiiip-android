package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.ValueObject

data class UserMetaInfo(
        val name: String,
        val description: String?,
        val profileImageUrl: String?
) : ValueObject