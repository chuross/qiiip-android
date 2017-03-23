package com.github.chuross.qiiip.application.preference

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.rejasupotaro.android.kvs.PrefsBuilder
import com.securepreferences.SecurePreferences

class AccountPreferencesBuilder : PrefsBuilder<AccountPreferences> {

    override fun build(context: Context?): AccountPreferences {
        return AccountPreferences(SecurePreferences(context, "${context!!.packageName}/${Settings.app.salt}", null))
    }
}