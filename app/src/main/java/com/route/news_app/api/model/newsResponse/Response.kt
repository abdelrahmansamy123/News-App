package com.route.news_app.api.model.newsResponse

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("image")
    val image: Image? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("imageId")
    val imageId: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("skin_color")
    val skinColor: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("hair_color")
    val hairColor: String? = null,

    @field:SerializedName("hair_type")
    val hairType: String? = null,

    @field:SerializedName("cloudFolder")
    val cloudFolder: String? = null,

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("eye_color")
    val eyeColor: String? = null,

    @field:SerializedName("isClosed")
    val isClosed: String? = null,

    @field:SerializedName("createdBy")
    val createdBy: String? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class ProfileImage(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class User(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profileImage")
    val profileImage: ProfileImage? = null,

    @field:SerializedName("_id")
    val id: String? = null
)

data class Image(

    @field:SerializedName("images")
    val images: List<ImagesItem?>? = null,

    @field:SerializedName("__v")
    val v: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null,

    @field:SerializedName("postId")
    val postId: String? = null
)

data class ImagesItem(

    @field:SerializedName("featureVector")
    val featureVector: List<Any?>? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("_id")
    val _id: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
