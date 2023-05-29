package com.roxasjearom.hellovalorant.data.mapper

import com.roxasjearom.hellovalorant.data.remote.response.AgentDto
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.Role

fun AgentDto.toAgent() = Agent(
    uuid = uuid,
    displayName = displayName,
    fullPortrait = fullPortrait,
    background = background,
    roleIcon = role.displayIcon,
)

fun AgentDto.toAgentDetails() = AgentDetails(
    uuid = uuid,
    abilities = abilities,
    background = background,
    description = description,
    displayIcon = displayIcon,
    displayName = displayName,
    fullPortrait = fullPortrait,
    role = Role(
        uuid = role.uuid,
        description = role.description,
        displayIcon = role.displayIcon,
        displayName = role.displayName,
    ),
)
