package com.roxasjearom.hellovalorant.domain.model

data class AgentDetails(
    val uuid: String,
    val abilities: List<Ability>,
    val background: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val role: Role,
    val backgroundGradientColors: List<String>,
)

data class Role(
    val uuid: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
)

data class Ability(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String,
)
