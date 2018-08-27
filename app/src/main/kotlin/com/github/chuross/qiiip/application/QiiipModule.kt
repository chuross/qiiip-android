package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.BuildConfig
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class QiiipModule(private val application: Application) {

    private val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    private val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BASIC })
                }
                it.addInterceptor {
                    it.proceed(it.request().newBuilder().also { builder ->
                        application.accountPreferences.token?.let {
                            if (it.isNotBlank()) builder.header("Authorization", "Bearer $it")
                        }
                    }.build())
                }
            }.build())
            .baseUrl("${Settings.qiita.apiUrl}/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun providesApplication() = application

    @Provides
    fun providesQiitaV2Api() = retrofit.create(QiitaV2Api::class.java)
}