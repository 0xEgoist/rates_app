package com.example.data.repository.remote.rates

import com.example.data.repository.remote.rates.models.RatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRatesApi {

    companion object {
        const val RATES = "api/android/latest"
        const val BASE_QUERY = "base"
    }

    @GET(RATES)
    suspend fun getRatesResultAsync(
        @Query(BASE_QUERY) baseCurrency: String
    ): Response<RatesResponse>
}