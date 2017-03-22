package com.github.chuross.qiiip.ui.viewmodel

import android.content.Context
import com.github.chuross.qiiip.application.Application
import io.reactivex.disposables.CompositeDisposable

interface ViewModel {

    val context: Context
    val application: Application get() = Application.from(context)
    val disposables: CompositeDisposable

    fun create() {
    }

    fun destroy() {
        disposables.clear()
    }
}