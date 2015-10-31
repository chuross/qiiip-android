package com.github.chuross.qiiip.ui.activity

import android.app.Activity
import android.os.Bundle
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val retrofit = Retrofit.Builder()
                .baseUrl(QiitaV2Api.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(QiitaV2Api::class.java)

    }
}
