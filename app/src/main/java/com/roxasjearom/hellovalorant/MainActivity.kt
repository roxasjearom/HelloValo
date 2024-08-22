package com.roxasjearom.hellovalorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
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
import com.roxasjearom.hellovalorant.presentation.agents.AgentScreen
import com.roxasjearom.hellovalorant.presentation.agents.AgentViewModel
import com.roxasjearom.hellovalorant.presentation.profile.ProfileScreen
import com.roxasjearom.hellovalorant.presentation.profile.ProfileViewModel
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
            val currentScreen = getScreen(
                backStackEntry?.destination?.route ?: Screen.Agents.name
            )

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    HelloValoAppBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() },
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Agents.name,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Agents.name) {
                        val agentViewModel: AgentViewModel = hiltViewModel()
                        AgentScreen(
                            agents = agentViewModel.agentUiState.collectAsStateWithLifecycle().value.agents,
                            onAgentClicked = { agentUuid ->
                                navController.navigate("profile/$agentUuid")
                            })
                    }
                    composable("${Screen.Profile.name}/{agentUuid}") {
                        val profileViewModel: ProfileViewModel = hiltViewModel()
                        ProfileScreen(agentUiState = profileViewModel.agentDetailsUiState.collectAsStateWithLifecycle().value)
                    }
                }
            }
        }
    }

    @Composable
    fun HelloValoAppBar(
        currentScreen: Screen,
        modifier: Modifier = Modifier,
        canNavigateBack: Boolean,
        navigateUp: () -> Unit = {},
        scrollBehavior: TopAppBarScrollBehavior?,
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(stringResource(id = currentScreen.title).uppercase(),)
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

    //Temporary getter for screen enum until Type-safe navigation is not yet stable
    private fun getScreen(route: String): Screen {
        return if (route.contains("Profile")) {
            Screen.Profile
        } else {
            Screen.Agents
        }
    }
}

enum class Screen(@StringRes val title: Int) {
    Agents(title = R.string.agents_title),
    Profile(title = R.string.empty_label),
}
