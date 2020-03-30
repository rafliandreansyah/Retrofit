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


/**    Perbedaan put dan patch yaitu put digunakan untuk pebaharuan semua data, seumpama data yang diperbaharui title, text
 *      Salah satunya bernilai null seumpama title bernilai null maka dalam penyimpanan tersebut title bernilai null, berbeda
 *      dengan patch diperuntukan untuk pembaharuan tidak semua, seumpama title bernilai null atau kosng maka, data title akan megambil
 *      data dari sebelumnya., gunakan put sebagai pembaharuan yang diperuntukkan untuk semua, dan gunakan patch sebagai pembaharuan
 *      beberapa.
**/
//    Put retrofit
    @PUT("posts/{id}")
    fun putPost(@Path("id") id: Int?)

//    Patch retrofit
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id: Int?)

}