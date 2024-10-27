package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AbilityUrlsResponse
import retrofit2.http.GET

interface SecondaryApi {
    @GET("/api/valorant-agent-abilities.json")
    suspend fun getAbilityUrls(): AbilityUrlsResponse
}
