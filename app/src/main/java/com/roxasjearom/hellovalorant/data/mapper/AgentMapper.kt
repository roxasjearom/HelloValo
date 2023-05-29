package com.roxasjearom.hellovalorant.data.mapper

import com.roxasjearom.hellovalorant.data.remote.response.AgentDto
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.model.AgentDetails

fun AgentDto.toAgent() = Agent(
    uuid = uuid,
    displayName = displayName,
    fullPortrait = fullPortrait,
    background = background,
)

fun AgentDto.toAgentDetails() = AgentDetails(
    abilities = abilities,
    assetPath = assetPath,
    background = background,
    backgroundGradientColors = backgroundGradientColors,
    bustPortrait = bustPortrait,
    description = description,
    developerName = developerName,
    displayIcon = displayIcon,
    displayIconSmall = displayIconSmall,
    displayName = displayName,
    fullPortrait = fullPortrait,
    fullPortraitV2 = fullPortraitV2,
    isAvailableForTest = isAvailableForTest,
    isBaseContent = isBaseContent,
    isFullPortraitRightFacing = isFullPortraitRightFacing,
    isPlayableCharacter = isPlayableCharacter,
    killfeedPortrait = killfeedPortrait,
    role = role,
    uuid = uuid,
    voiceLine = voiceLine,
)
