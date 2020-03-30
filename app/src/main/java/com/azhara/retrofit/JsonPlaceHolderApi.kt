package com.azhara.retrofit

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    // Melakukan pengambilan data
    @GET("posts")
    fun getPost(): Call<List<Post>>

    @GET("posts")
    fun getPostByUserId(@Query("userId") userId: Int): Call<List<Post>>

    @GET("posts/{idPost}/comments") //Get path by postId
    fun getComments(@Path("idPost") postId: Int): Call<List<Comment>>

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>


    @FormUrlEncoded
    @POST("posts")
    fun createPost(@Field("userId") userId: Int,
                   @Field("title") title: String,
                   @Field("body") text: String
    ): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap field: Map<String, String>): Call<Post>
}
