package com.azhara.retrofit

import com.google.gson.annotations.SerializedName

data class Post(
    var userId: Int?,
    var id: Int? = 0,
    var title: String?,

    @SerializedName("body")
    var text: String?
)