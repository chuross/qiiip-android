package com.github.chuross.qiiip.infrastructure.qiita.v2

import com.github.chuross.qiiip.infrastructure.qiita.v2.parameter.TokenParameter
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Item
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Tag
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Token
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.User
import io.reactivex.Single
import retrofit2.http.*

interface QiitaV2Api {

    @POST("/api/v2/access_tokens")
    fun login(@Body parameter: TokenParameter): Single<Token>

    @GET("/api/v2/authenticated_user")
    fun getAuthenticatedUser(): Single<User>

    @GET("api/v2/items/{item_id}")
    fun getItemById(@Path("item_id") itemId: String): Single<Item>

    @GET("api/v2/items")
    fun getItems(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("api/v2/items")
    fun getItemsByKeyword(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("api/v2/users/{user_id}/items")
    fun getItemsByUserId(@Path("user_id") userId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("api/v2/tags/{tag_id}/items")
    fun getItemsByTagId(@Path("tag_id") tagId: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Item>>

    @GET("api/v2/tags/{tag_id}")
    fun getTagById(@Path("tag_id") tagId: String): Single<Tag>

    @GET("api/v2/tags")
    fun getTags(@Query("page") page: Int, @Query("per_page") perPage: Int): Single<MutableList<Tag>>
}