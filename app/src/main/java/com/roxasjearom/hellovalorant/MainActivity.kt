package com.roxasjearom.hellovalorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.roxasjearom.hellovalorant.navigation.Route
import com.roxasjearom.hellovalorant.presentation.agents.AgentScreen
import com.roxasjearom.hellovalorant.presentation.agents.AgentViewModel
import com.roxasjearom.hellovalorant.presentation.agentdetails.AgentDetailsScreen
import com.roxasjearom.hellovalorant.presentation.agentdetails.AgentDetailsViewModel
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloValoApp()
        }
    }


    @Composable
    fun HelloValoApp() {
        HelloValoTheme {
            val navController = rememberNavController()
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = getScreenTitle(
                backStackEntry?.destination?.route ?: Route.Agents.toString()
            )

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    AnimatedVisibility(
                        visible = currentScreen.equals(Route.Agents.toString(), true),
                        enter = slideInVertically(initialOffsetY = { -it }),
                        exit = slideOutVertically(targetOffsetY = { -it }),
                        content = {
                            HelloValoAppBar(
                                currentScreen = currentScreen,
                                canNavigateBack = navController.previousBackStackEntry != null,
                                navigateUp = { navController.navigateUp() },
                                scrollBehavior = scrollBehavior,
                            )
                        }
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Route.Agents,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable<Route.Agents> {
                        val agentViewModel: AgentViewModel = hiltViewModel()
                        AgentScreen(
                            agents = agentViewModel.agentUiState.collectAsStateWithLifecycle().value.agents,
                            onAgentClicked = { agentUuid ->
                                navController.navigate(Route.Profile(agentUuid))
                            })
                    }
                    composable<Route.Profile> {
                        val agentDetailsViewModel: AgentDetailsViewModel = hiltViewModel()
                        AgentDetailsScreen(agentUiState = agentDetailsViewModel.agentDetailsUiState.collectAsStateWithLifecycle().value)
                    }
                }
            }
        }
    }

    @Composable
    fun HelloValoAppBar(
        currentScreen: String,
        modifier: Modifier = Modifier,
        canNavigateBack: Boolean,
        navigateUp: () -> Unit = {},
        scrollBehavior: TopAppBarScrollBehavior?,
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(currentScreen.uppercase())
            },
            modifier = modifier,
            navigationIcon = @Composable {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }

    private fun getScreenTitle(route: String): String {
        return if (route.contains("Profile")) {
            getString(R.string.empty_label)
        } else {
            getString(R.string.agents_title)
        }
    }
}
