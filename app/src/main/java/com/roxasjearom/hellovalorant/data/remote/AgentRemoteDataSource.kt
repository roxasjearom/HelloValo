package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDataResponse

class AgentRemoteDataSource(private val valorantApi: ValorantApi) {

    suspend fun getAgents(): AgentDataResponse {
        return valorantApi.getAgents()
    }
}
