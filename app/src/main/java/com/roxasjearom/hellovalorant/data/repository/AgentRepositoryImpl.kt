package com.roxasjearom.hellovalorant.data.repository

import com.roxasjearom.hellovalorant.data.mapper.toAgent
import com.roxasjearom.hellovalorant.data.remote.AgentRemoteDataSource
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository

class AgentRepositoryImpl(
    private val agentRemoteDataSource: AgentRemoteDataSource,
) : AgentRepository {
    override suspend fun getAgents(): List<Agent>{
        return agentRemoteDataSource.getAgents().data.map { it.toAgent() }
    }
}
