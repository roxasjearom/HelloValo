package com.roxasjearom.hellovalorant.data.mapper

import com.roxasjearom.hellovalorant.data.remote.response.AgentDto
import com.roxasjearom.hellovalorant.domain.model.Agent

fun AgentDto.toAgent() = Agent(
    uuid = uuid,
    displayName = displayName,
    fullPortrait = fullPortrait,
    background = background,
)
