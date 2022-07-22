package uz.gita.testapp.data.remote.api

import android.media.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import uz.gita.testapp.data.remote.response.ImageResponse

interface AppApi {

    @GET("v2/list")
    suspend fun getPicturesApi(): Response<List<ImageResponse>>


    @GET
    suspend fun getPicturesApi(@Url url:String): Response<ImageResponse>
}