package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.infrastructure.qiiip.Configs
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class QiiipModule(private val application: Application) {

    private val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl(Configs.qiitaApiBase)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesQiitaV2Api() = retrofit.create(QiitaV2Api::class.java)
}