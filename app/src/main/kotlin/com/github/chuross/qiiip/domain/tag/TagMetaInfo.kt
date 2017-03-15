package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.ValueObject

data class TagMetaInfo(
        val iconUrl: String?,
        val itemCount: Long?,
        val followersCount: Long?
) : ValueObject