package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.BuildConfig
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class QiiipModule(private val application: Application) {

    private val client: OkHttpClient get() {
        return OkHttpClient.Builder().apply {
           if (!BuildConfig.DEBUG) return@apply

           addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        }.build()
    }
    private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Settings.qiita.apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesQiitaV2Api() = retrofit.create(QiitaV2Api::class.java)
}