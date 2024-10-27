package com.roxasjearom.hellovalorant.domain.repository

import com.roxasjearom.hellovalorant.domain.model.AbilityUrl
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.VideoUrl

interface AgentRepository {

    suspend fun getAgents(): List<Agent>

    suspend fun getAgentByUuid(uuid: String): AgentDetails

    suspend fun getAbilityUrls(): List<AbilityUrl>

    suspend fun getVideoUrlsByUuid(uuid: String): List<VideoUrl>

}
