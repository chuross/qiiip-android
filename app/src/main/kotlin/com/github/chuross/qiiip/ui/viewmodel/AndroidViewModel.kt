package com.github.chuross.qiiip.ui.viewmodel

import android.content.Context
import com.github.chuross.qiiip.application.Application

abstract class AndroidViewModel : BaseViewModel() {

    abstract var context: Context
    val qiiipApplication: Application get() = Application.from(context)
}