package com.github.chuross.qiiip.application

import android.content.Context
import android.os.AsyncTask
import com.github.chuross.qiiip.BuildConfig
import com.github.chuross.qiiip.application.preference.AccountPreferences
import com.github.chuross.qiiip.domain.user.User
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class Application : android.app.Application() {

    companion object {
        fun from(context: Context): Application = context.applicationContext as Application
    }

    val mainThreadScheduler: Scheduler get() = AndroidSchedulers.mainThread()
    val serialScheduler: Scheduler get() = Schedulers.from(AsyncTask.SERIAL_EXECUTOR)
    val threadPoolScheduler: Scheduler get() = Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
    val component: QiiipComponent by lazy {
        DaggerQiiipComponent.builder()
                .qiiipModule(QiiipModule(this))
                .build()
    }
    val isAuthorized: Boolean get() = accountPreferences.hasUser() && accountPreferences.hasToken()
    val authorizedUser: User? get() = if (accountPreferences.hasToken()) accountPreferences.user else null
    val accountPreferences: AccountPreferences get() = AccountPreferences.get(this)
    val useCases: UseCases by lazy { UseCases(this) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Picasso.setSingletonInstance(Picasso.Builder(this).downloader(OkHttp3Downloader(this)).build())
    }

}