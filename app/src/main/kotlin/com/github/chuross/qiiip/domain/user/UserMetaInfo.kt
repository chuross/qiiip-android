package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.common.ValueObject

data class UserMetaInfo(
        val name: String,
        val description: String?,
        val profileImageUrl: String?,
        val websideUrl: String?
) : ValueObject