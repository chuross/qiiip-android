package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.common.ValueObject

data class TagMetaInfo(
        val iconUrl: String?,
        val itemCount: Long?,
        val followersCount: Long?
) : ValueObject