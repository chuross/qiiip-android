package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import android.content.Context
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.ui.viewmodel.AndroidViewModel
import com.github.chuross.viewmodelargs.annotation.Argument
import com.github.chuross.viewmodelargs.annotation.ViewModelWithArgs

@ViewModelWithArgs
class HomeScreenFragmentViewModel : AndroidViewModel() {

    @Argument
    override lateinit var context: Context

    val title: String get() = context.getString(R.string.app_name)
    val defaultTabIndex: Int get() = if (qiiipApplication.isAuthorized) 1 else 0
}