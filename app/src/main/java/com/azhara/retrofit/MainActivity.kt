package com.azhara.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

//        getPost()
//        getPostsById(3)
//        getComment(4)
        createPost()

    }

    private fun createPost(){
        val post = Post(userId = 24, title = "Post pertama", text = "Hello retrofit")

        val field = HashMap<String, String>()
        field.put("userId", "26")
        field.put("title", "Rafli the God of technology")
        field.put("body", "Some day lazy....... and the people call him father of mobile technology")

        val call: Call<Post> = jsonPlaceHolderApi.createPost(field)

        call.enqueue(object : Callback<Post>{
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val createPost = response.body()

                var content = ""
                content += "Code Status: ${response.code()} \n"
                content += "userId: ${createPost?.userId} \n"
                content += "id: ${createPost?.id}\n"
                content += "title: ${createPost?.title} \n"
                content += "text: ${createPost?.text} \n"

                text_result.text = content
            }

        })
    }

    private fun getPost(){
        val call: Call<List<Post>> = jsonPlaceHolderApi.getPost()

        call.enqueue(object: Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                text_result.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful){
                    text_result.setText(response.code())
                    return
                }

                val dataPost = response.body()
                if (dataPost != null) {
                    for (post in dataPost){
                        var content = ""
                        content += "ID: ${post.id} \n"
                        content += "User ID: ${post.userId} \n"
                        content += "Title: ${post.title} \n"
                        content += "Text: ${post.text} \n\n"

                        text_result.append(content)
                    }
                }
            }

        })
    }

    private fun getPostsById(userId: Int){
        val call: Call<List<Post>> = jsonPlaceHolderApi.getPostByUserId(userId)

        call.enqueue(object : Callback<List<Post>>{
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                text_result.text = t.message
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful){
                    text_result.setText(response.code())
                    return
                }

                val dataPost = response.body()
                if (dataPost != null) {
                    for (post in dataPost){
                        var content = ""
                        content += "ID: ${post.id} \n"
                        content += "User ID: ${post.userId} \n"
                        content += "Title: ${post.title} \n"
                        content += "Text: ${post.text} \n\n"

                        text_result.append(content)
                    }
                }
            }

        })
    }

    private fun getComment(postId: Int){
        val call: Call<List<Comment>> = jsonPlaceHolderApi.getComments(postId)

        call.enqueue(object : Callback<List<Comment>>{
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                text_result.text = t.message
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (!response.isSuccessful){
                    text_result.setText(response.code())
                    return
                }

                val dataPost = response.body()
                if (dataPost != null) {
                    for (post in dataPost){
                        var content = ""
                        content += "ID: ${post.id} \n"
                        content += "Comment ID: ${post.postId} \n"
                        content += "Title: ${post.name} \n"
                        content += "Text: ${post.body} \n\n"

                        text_result.append(content)
                    }
                }
            }

        })
    }
}
