package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDetailsResponse
import com.roxasjearom.hellovalorant.data.remote.response.AgentsResponse
import javax.inject.Inject

class AgentRemoteDataSource @Inject constructor(
    private val valorantApi: ValorantApi
) {

    suspend fun getAgents(): AgentsResponse {
        return valorantApi.getAgents()
    }

    suspend fun getAgentByUuid(uuid: String): AgentDetailsResponse {
        return valorantApi.getAgentByUuid(uuid = uuid)
    }
}
