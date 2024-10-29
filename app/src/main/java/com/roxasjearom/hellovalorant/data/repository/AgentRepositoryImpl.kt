package com.roxasjearom.hellovalorant.data.repository

import com.roxasjearom.hellovalorant.data.mapper.toAbilityUrl
import com.roxasjearom.hellovalorant.data.mapper.toAgent
import com.roxasjearom.hellovalorant.data.mapper.toAgentDetails
import com.roxasjearom.hellovalorant.data.remote.AgentRemoteDataSource
import com.roxasjearom.hellovalorant.domain.model.AbilityUrl
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.VideoUrl
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository
import javax.inject.Inject

class AgentRepositoryImpl @Inject constructor(
    private val agentRemoteDataSource: AgentRemoteDataSource,
) : AgentRepository {

    private var localAbilityUrls: List<AbilityUrl> = emptyList()

    override suspend fun getAgents(): List<Agent> {
        return agentRemoteDataSource.getAgents().data.map { it.toAgent() }
    }

    override suspend fun getAgentByUuid(uuid: String): AgentDetails {
        return agentRemoteDataSource.getAgentByUuid(uuid).data.toAgentDetails()
    }

    override suspend fun getAbilityUrls(): List<AbilityUrl> {
        return when {
            localAbilityUrls.isEmpty() -> {
                val abilityUrlsFromRemote = agentRemoteDataSource.getAbilityUrls().data.map { it.toAbilityUrl() }
                localAbilityUrls = abilityUrlsFromRemote
                abilityUrlsFromRemote
            }
            else -> {
                localAbilityUrls
            }
        }
    }

    override suspend fun getVideoUrlsByUuid(uuid: String): List<VideoUrl> {
        return getAbilityUrls().firstOrNull { it.uuid == uuid }?.videoUrls ?: emptyList()
    }
}
