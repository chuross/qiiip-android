package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Response
import dagger.Module
import dagger.Provides
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

@Module
class QiipModule(val application: Application) {

    private val retrofit: Retrofit

    init {
        val client = OkHttpClient().apply {
            networkInterceptors().add(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain?): Response? {
                    return chain?.proceed(chain.request().newBuilder().apply {
                        application.preferences.getAccessToken()?.let {
                            header("Authorization", "Bearer $it")
                        }
                    }.build())
                }
            })
        }

        retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(application.requestContext.baseUrl.toString())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providesQiitaV2Api() = retrofit.create(QiitaV2Api::class.java)

}