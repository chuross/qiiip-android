package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import io.reactivex.Single
import javax.inject.Inject

class TagRepository {

    @Inject
    lateinit var api: QiitaV2Api

    fun findAllByUserIdentity(userIdentity: UserIdentity, page: Int, perPage: Int): Single<List<Tag>> {
        return api.getTagsByUserId(userIdentity.value, page, perPage)
                .map { TagConverter.toModels(it) }
    }
}