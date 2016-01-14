package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.tag.converter.TagConverter
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import rx.Observable
import javax.inject.Inject

class TagRepository {

    @Inject
    lateinit var api: QiitaV2Api

    fun find(identity: TagIdentity): Observable<Tag> = api.getTagById(identity.value)
            .map { TagConverter.convertToModel(it) }

    fun findAll(page: Int, perPage: Int): Observable<List<Tag>> = api.getTags(page, perPage)
            .map { result -> TagConverter.convertToModels(result) }

}