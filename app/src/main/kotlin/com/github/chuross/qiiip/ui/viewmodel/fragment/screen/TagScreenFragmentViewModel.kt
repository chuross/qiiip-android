package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.viewmodel.BaseViewModel
import com.github.chuross.viewmodelargs.annotation.Argument
import com.github.chuross.viewmodelargs.annotation.ViewModelWithArgs

@ViewModelWithArgs
class TagScreenFragmentViewModel : BaseViewModel() {

    @Argument
    lateinit var tag: Tag
}