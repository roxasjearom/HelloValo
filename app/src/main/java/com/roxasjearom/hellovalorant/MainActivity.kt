package com.roxasjearom.hellovalorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(stringResource(id = R.string.app_name))
                        },
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "agents",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("agents") {
                        val agentViewModel: AgentViewModel = hiltViewModel()
                        AgentScreen(
                            agents = agentViewModel.agentUiState.collectAsStateWithLifecycle().value.agents,
                            onAgentClicked = { agentUuid ->
                                navController.navigate("profile/$agentUuid")
                            })
                    }
                    composable("profile/{agentUuid}") {
                        val profileViewModel: ProfileViewModel = hiltViewModel()
                        ProfileScreen(agentUiState = profileViewModel.agentDetailsUiState.collectAsStateWithLifecycle().value)
                    }
                }
            }
        }
    }
}
