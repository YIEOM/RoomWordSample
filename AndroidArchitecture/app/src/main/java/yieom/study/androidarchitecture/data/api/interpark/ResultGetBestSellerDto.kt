package yieom.study.androidarchitecture.data.api.interpark

import com.google.gson.annotations.SerializedName


data class ResultGetBestSellerDto(
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>
)

data class Book(
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String
)
