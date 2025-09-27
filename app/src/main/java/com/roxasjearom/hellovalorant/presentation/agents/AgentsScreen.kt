package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roxasjearom.hellovalorant.R
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.presentation.common.FullScreenLoadingIndicator

@Composable
fun AgentRoute(
    agentViewModel: AgentViewModel = hiltViewModel(),
    onAgentClicked: (String) -> Unit,
) {
    val uiState by agentViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        FullScreenLoadingIndicator()
    } else {
        AgentScreen(
            agents = uiState.agents,
            onAgentClicked = onAgentClicked,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentScreen(
    agents: List<Agent>,
    modifier: Modifier = Modifier,
    onAgentClicked: (String) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.agents_title).uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = modifier,
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
