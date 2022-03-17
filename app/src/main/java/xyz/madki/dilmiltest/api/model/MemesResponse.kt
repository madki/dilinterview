package xyz.madki.dilmiltest.api.model

import com.google.gson.annotations.SerializedName

data class MemesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") private val data: MemesWrapper
) {
    fun getMemes() = data.memes
}

data class MemesWrapper(
    @SerializedName("memes") val memes: List<MemeApiModel>
)

data class MemeApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("box_count") val boxCount: Int
)