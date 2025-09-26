package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.presentation.common.FullScreenLoadingIndicator

@Composable
fun AgentRoute(agentViewModel: AgentViewModel = hiltViewModel(), onAgentClicked: (String) -> Unit) {
    val uiState by agentViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        FullScreenLoadingIndicator()
    } else {
        AgentScreen(
            agents = uiState.agents,
            onAgentClicked = onAgentClicked
        )
    }
}

@Composable
fun AgentScreen(
    agents: List<Agent>,
    modifier: Modifier = Modifier,
    onAgentClicked: (String) -> Unit,
) {
    AgentGridList(
        agents = agents,
        modifier = modifier.padding(horizontal = 8.dp),
        onAgentClicked = onAgentClicked,
    )
}
