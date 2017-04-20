package com.github.chuross.qiiip.application

import android.content.Context
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.usecase.AuthorizeAccount
import com.github.chuross.qiiip.usecase.item.GetFeedItems
import com.github.chuross.qiiip.usecase.item.GetItems
import com.github.chuross.qiiip.usecase.item.GetItemsByTagId
import com.github.chuross.qiiip.usecase.item.GetStokeItems
import io.reactivex.Single

typealias UseCaseTransformer<T> = (Single<T>) -> Single<T>

class UseCases(val context: Context) {

    private val application: Application get() = Application.from(context)
    private val component: QiiipComponent get() = application.component

    fun authorizeAccount(code: String) = AuthorizeAccount(code).apply { component.inject(this) }

    fun getItems(page: Int, perPage: Int) = GetItems(page, perPage).apply { component.inject(this) }

    fun getItemsByTagId(tagIdentity: TagIdentity, page: Int, perPage: Int) = GetItemsByTagId(tagIdentity, page, perPage).apply { component.inject(this) }

    fun getMyStockItems(page: Int, perPage: Int) = GetStokeItems(page, perPage).apply { component.inject(this) }

    fun getFeedItems(page: Int, perPage: Int) = GetFeedItems(page, perPage).apply { component.inject(this) }
}