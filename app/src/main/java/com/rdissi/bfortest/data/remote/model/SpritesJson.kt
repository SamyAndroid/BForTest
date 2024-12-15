package com.rdissi.bfortest.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpritesJson(
    @SerializedName("front_default")
    val frontDefaultImageUrl: String,

    @SerializedName("other")
    val otherJson: OtherJson
)

data class OtherJson(
    @SerializedName("dream_world")
    val imageJson: ImageJson
)

data class ImageJson(
    @SerializedName("front_default")
    val defaultImageUrl: String
)
