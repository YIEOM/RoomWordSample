package yieom.study.androidarchitecture.data.api.interpark

import retrofit2.Call
import retrofit2.http.*

interface InterparkAPI {
    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSeller(
        @Query("key") apiKey: String
    ): Call<ResultGetBestSellerDto>
}