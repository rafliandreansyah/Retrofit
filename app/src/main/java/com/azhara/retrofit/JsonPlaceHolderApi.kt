package com.azhara.retrofit

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    // Melakukan pengambilan data
    @GET("posts")
    fun getPost(): Call<List<Post>>


//    Get by UserId menggunakan query
    @GET("posts")
    fun getPostByUserId(@Query("userId") userId: Int): Call<List<Post>>

//    Pengambilan data dapat penggunakan Path dimana penulisan pada @Get diikuti {value} dan menggunakan  @Path parameter sesuai dengan {value} pada @Get
    @GET("posts/{idPost}/comments") //Get path by postId
    fun getComments(@Path("idPost") postId: Int): Call<List<Comment>>


//    Create data
    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>


//    create data menggunakan  Field dan Form Url encode, secara otomatis retrofit akan menuliskan url berdasarkan field pada parameter
    @FormUrlEncoded
    @POST("posts")
    fun createPost(@Field("userId") userId: Int,
                   @Field("title") title: String,
                   @Field("body") text: String
    ): Call<Post>

//    Create data menggunakan Hashmap yang berisi key & value
    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap field: Map<String, String>): Call<Post>

    @PUT("posts/{id}")
    fun putPost(@Path("id") id: Int?)
}