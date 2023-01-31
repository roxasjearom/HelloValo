package com.roxasjearom.hellovalorant.domain.repository

import com.roxasjearom.hellovalorant.domain.model.Agent

interface AgentRepository {

    suspend fun getAgents(): List<Agent>

}
