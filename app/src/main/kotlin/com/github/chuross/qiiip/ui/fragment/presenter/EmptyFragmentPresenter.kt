package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter

abstract class EmptyFragmentPresenter<F : Fragment>(fragment: F) : SupportFragmentPresenter<F>(fragment) {

}