package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.roxasjearom.hellovalorant.R
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.presentation.common.FullScreenLoadingIndicator

@Composable
fun AgentRoute(
    agentViewModel: AgentViewModel = hiltViewModel(),
    navController: NavHostController,
    onAgentClicked: (String) -> Unit,
) {
    val uiState by agentViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        FullScreenLoadingIndicator()
    } else {
        AgentScreen(
            agents = uiState.agents,
            navController = navController,
            onAgentClicked = onAgentClicked,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentScreen(
    agents: List<Agent>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onAgentClicked: (String) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HelloValoAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        AgentGridList(
            agents = agents,
            modifier = modifier.padding(horizontal = 8.dp),
            contentPadding = innerPadding,
            onAgentClicked = onAgentClicked,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloValoAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior?,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.agents_title).uppercase(),
                style = MaterialTheme.typography.titleLarge,
            )
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
