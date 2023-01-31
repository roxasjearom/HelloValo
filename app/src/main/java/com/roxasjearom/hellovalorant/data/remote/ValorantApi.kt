package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDataResponse

interface ValorantApi {

    suspend fun getAgents(): AgentDataResponse
}
