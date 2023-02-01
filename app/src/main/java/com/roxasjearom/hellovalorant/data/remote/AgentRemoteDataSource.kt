package com.roxasjearom.hellovalorant.data.remote

import com.roxasjearom.hellovalorant.data.remote.response.AgentDataResponse
import javax.inject.Inject

class AgentRemoteDataSource @Inject constructor(private val valorantApi: ValorantApi) {

    suspend fun getAgents(): AgentDataResponse {
        return valorantApi.getAgents()
    }
}
