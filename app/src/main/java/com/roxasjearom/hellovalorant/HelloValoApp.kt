@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.roxasjearom.hellovalorant

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roxasjearom.hellovalorant.navigation.Route
import com.roxasjearom.hellovalorant.presentation.agentdetails.AgentDetailsRoute
import com.roxasjearom.hellovalorant.presentation.agents.AgentRoute

@Composable
fun HelloValoApp() {
    val navController = rememberNavController()

    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.Agents,
            ) {
                composable<Route.Agents> {
                    CompositionLocalProvider(
                        LocalAnimatedVisibilityScope provides this,
                    ) {
                        AgentRoute(
                            onAgentClicked = { agentUuid ->
                                navController.navigate(Route.Profile(agentUuid))
                            }
                        )
                    }
                }
                composable<Route.Profile> {
                    CompositionLocalProvider(
                        LocalAnimatedVisibilityScope provides this,
                    ) {
                        AgentDetailsRoute()
                    }
                }
            }
        }
    }
}

val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
