package com.github.chuross.qiiip.infrastructure.qiita

import com.github.chuross.qiiip.infrastructure.qiita.resource.Item
import com.github.chuross.qiiip.infrastructure.qiita.resource.Tag
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

interface QiitaV2Api {

    companion object {
        val BASE_URL = "http://qiita.com/"
    }

    @GET("api/v2/items/{item_id}")
    fun getItemById(@Path("item_id") itemId: String): Observable<Item>

    @GET("api/v2/items")
    fun getItemsByKeyword(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/tags/{tag_id}/items")
    fun getItemsByTagId(@Path("tag_id") tagId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/tags")
    fun getTags(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Tag>>
}