package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDataResponse
import retrofit2.http.GET

interface ValorantApi {

    @GET("/v1/agents?isPlayableCharacter=true")
    suspend fun getAgents(): AgentDataResponse
}
