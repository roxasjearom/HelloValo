package com.roxasjearom.hellovalorant.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Agents: Route()

    @Serializable
    data class Profile(val id: String): Route()
}
