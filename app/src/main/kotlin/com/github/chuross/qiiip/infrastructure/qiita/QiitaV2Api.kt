package com.github.chuross.qiiip.infrastructure.qiita

import android.net.Uri
import com.github.chuross.qiiip.infrastructure.qiita.request.TokenRequest
import com.github.chuross.qiiip.infrastructure.qiita.resource.Item
import com.github.chuross.qiiip.infrastructure.qiita.resource.Tag
import com.github.chuross.qiiip.infrastructure.qiita.resource.Token
import com.github.chuross.qiiip.infrastructure.qiita.resource.User
import retrofit.http.*
import rx.Observable

interface QiitaV2Api {

    companion object {
        val BASE_URL = "http://qiita.com"

        fun getAuthorizationUri(context: RequestContext, state: String): Uri {
            return Uri.Builder().apply {
                scheme(context.baseUrl.scheme)
                authority(context.baseUrl.authority)
                path("api/v2/oauth/authorize")
                appendQueryParameter("scope", "read_qiita write_qiita")
                appendQueryParameter("state", state)
                appendQueryParameter("client_id", context.clientId)
            }.build()
        }
    }

    @POST("/api/v2/access_tokens")
    fun login(@Body request: TokenRequest): Observable<Token>

    @GET("/api/v2/authenticated_user")
    fun getAuthenticatedUser(): Observable<User>

    @GET("api/v2/items/{item_id}")
    fun getItemById(@Path("item_id") itemId: String): Observable<Item>

    @GET("api/v2/items")
    fun getItems(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/items")
    fun getItemsByKeyword(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/users/{user_id}/items")
    fun getItemsByUserId(@Path("user_id") userId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/tags/{tag_id}/items")
    fun getItemsByTagId(@Path("tag_id") tagId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Item>>

    @GET("api/v2/tags")
    fun getTags(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<MutableList<Tag>>
}