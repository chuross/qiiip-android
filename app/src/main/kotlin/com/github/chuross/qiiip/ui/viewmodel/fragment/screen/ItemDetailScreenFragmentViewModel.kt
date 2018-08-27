package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import android.content.Context
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.AndroidViewModel
import com.github.chuross.qiiip.usecase.UseCaseTransformer
import com.github.chuross.viewmodelargs.annotation.Argument
import com.github.chuross.viewmodelargs.annotation.ViewModelWithArgs
import jp.keita.kagurazaka.rxproperty.RxProperty

@ViewModelWithArgs
class ItemDetailScreenFragmentViewModel : AndroidViewModel() {

    @Argument
    override lateinit var context: Context

    @Argument
    lateinit var item: Item

    val isStoked: RxProperty<Boolean> = RxProperty(false)
    private val transformer: UseCaseTransformer<Boolean> = {
        it.subscribeOn(qiiipApplication.serialScheduler)
                .observeOn(qiiipApplication.mainThreadScheduler)
    }

    init {
        qiiipApplication.useCases.isStockItem(item.identity)
                .compose(transformer)
                .apply { disposables.add(this) }
                .exec({ isStoked.set(true) })
    }

    fun toggleStock() {
        if (!qiiipApplication.isAuthorized) {
            // TODO dialog
        }

        if (isStoked.get() ?: false) {
            qiiipApplication.useCases.removeStockItem(item.identity)
                    .compose(transformer)
                    .apply { disposables.add(this) }
                    .exec({ isStoked.set(false) })
        } else {
            qiiipApplication.useCases.addStockItem(item.identity)
                    .compose(transformer)
                    .apply { disposables.add(this) }
                    .exec({ isStoked.set(true) })
        }
    }
}