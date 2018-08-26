package com.github.chuross.qiiip.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}