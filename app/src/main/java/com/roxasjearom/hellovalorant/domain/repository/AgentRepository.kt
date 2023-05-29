package com.roxasjearom.hellovalorant.domain.repository

import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.model.AgentDetails

interface AgentRepository {

    suspend fun getAgents(): List<Agent>

    suspend fun getAgentByUuid(uuid: String): AgentDetails

}
