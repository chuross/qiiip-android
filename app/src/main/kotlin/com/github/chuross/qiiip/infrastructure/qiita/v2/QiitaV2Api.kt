package com.github.chuross.qiiip.infrastructure.qiita.v2

import com.github.chuross.qiiip.infrastructure.qiita.v2.parameter.TokenParameter
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Item
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Tag
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Token
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.User
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.http.*

interface QiitaV2Api {

    companion object {
        fun getQiitaAuthUrl(apiUrl: String, clientId: String, state: String): String {
            return HttpUrl.parse("$apiUrl/oauth/authorize").newBuilder()
                    .addQueryParameter("scope", "read_qiita write_qiita")
                    .addQueryParameter("state", state)
                    .addQueryParameter("client_id", clientId)
                    .toString()
        }
    }

    @POST("access_tokens")
    fun login(@Body parameter: TokenParameter): Single<Token>

    @GET("authenticated_user")
    fun getAuthenticatedUser(): Single<User>

    @GET("items/{item_id}")
    fun getItemById(@Path("item_id") itemId: String): Single<Item>

    @GET("items")
    fun getItems(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("items")
    fun getItemsByKeyword(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("users/{user_id}/items")
    fun getItemsByUserId(@Path("user_id") userId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("users/{user_id}/stocks")
    fun getStocksByUserId(@Path("user_id") userId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("tags/{tag_id}/items")
    fun getItemsByTagId(@Path("tag_id") tagId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("tags")
    fun getTags(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Tag>>

    @GET("users/{user_id}/following_tags")
    fun getTagsByUserId(@Path("user_id") userId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Tag>>

    @GET("tags/{tag_id}")
    fun getTagById(@Path("tag_id") tagId: String): Single<Tag>
}