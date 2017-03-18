package com.github.chuross.qiiip.application

import android.content.Context
import android.os.AsyncTask
import com.github.chuross.qiiip.BuildConfig
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class Application: android.app.Application() {

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

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}