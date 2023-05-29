package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDetailsResponse
import com.roxasjearom.hellovalorant.data.remote.response.AgentsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {

    @GET("/v1/agents?isPlayableCharacter=true")
    suspend fun getAgents(): AgentsResponse

    @GET("/v1/agents/{uuid}")
    suspend fun getAgentByUuid(@Path("uuid") uuid: String): AgentDetailsResponse
}
