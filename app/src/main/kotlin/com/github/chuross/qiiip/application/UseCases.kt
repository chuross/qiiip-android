package com.github.chuross.qiiip.application

import android.content.Context
import com.github.chuross.qiiip.usecase.AuthorizeAccount

class UseCases(val context: Context) {

    private val application: Application get() = Application.from(context)

    fun authorizeAccount(code: String) = AuthorizeAccount(code).apply { this@UseCases.application.component.inject(this) }
}