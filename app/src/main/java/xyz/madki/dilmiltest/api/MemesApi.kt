package xyz.madki.dilmiltest.api

import retrofit2.Response
import retrofit2.http.GET
import xyz.madki.dilmiltest.api.model.MemesResponse

interface MemesApi {
    @GET("/get_memes")
    suspend fun getMemes(): Response<MemesResponse>

    companion object {
        const val BASE_URL = "https://api.imgflip.com"
    }
}