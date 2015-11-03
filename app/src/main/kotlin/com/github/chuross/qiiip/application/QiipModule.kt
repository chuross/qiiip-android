package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import dagger.Module
import dagger.Provides
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

@Module
class QiipModule {

    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(QiitaV2Api.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providesQiitaV2Api() = retrofit.create(QiitaV2Api::class.java)

}