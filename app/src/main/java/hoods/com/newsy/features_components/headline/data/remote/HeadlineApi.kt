package hoods.com.newsy.features_components.headline.data.remote

import hoods.com.newsy.features_components.core.data.remote.models.NewsyRemoteDto
import hoods.com.newsy.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadlineApi {
    companion object {
        private const val  HEADLINE_END_POINT = "/v2/top-headlines"
    }

    @GET(HEADLINE_END_POINT)
    suspend fun getHeadlines(
        @Query("apiKey") key: String = K.API_KEY,
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsyRemoteDto
}