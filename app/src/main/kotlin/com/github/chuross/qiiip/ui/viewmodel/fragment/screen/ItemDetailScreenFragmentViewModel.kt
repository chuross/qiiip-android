package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import android.content.Context
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.FragmentViewModel
import com.github.chuross.qiiip.usecase.UseCaseTransformer
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import jp.keita.kagurazaka.rxproperty.RxProperty

class ItemDetailScreenFragmentViewModel(context: Context, val item: Item) : FragmentViewModel(context) {

    val isStoked: RxProperty<Boolean> = RxProperty(false)
    private val transformer: UseCaseTransformer<Boolean> = {
        it.bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribeOn(application.serialScheduler)
                .observeOn(application.mainThreadScheduler)
    }

    override fun create() {
        super.create()
        application.useCases.isStockItem(item.identity)
                .compose(transformer)
                .apply { disposables.add(this) }
                .exec({ isStoked.set(true) })
    }

    fun toggleStock() {
        if (!application.isAuthorized) {
            // TODO dialog
        }

        if (isStoked.get() ?: false) {
            application.useCases.removeStockItem(item.identity)
                    .compose(transformer)
                    .apply { disposables.add(this) }
                    .exec({ isStoked.set(false) })
        } else {
            application.useCases.addStockItem(item.identity)
                    .compose(transformer)
                    .apply { disposables.add(this) }
                    .exec({ isStoked.set(true) })
        }
    }
}