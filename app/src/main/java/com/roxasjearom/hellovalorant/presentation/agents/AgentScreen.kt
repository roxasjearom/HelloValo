package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roxasjearom.hellovalorant.domain.model.Agent


@Composable
fun AgentScreen(agents: List<Agent>, onAgentClicked: (String) -> Unit) {
    AgentGridList(
        agents = agents,
        onAgentClicked = onAgentClicked,
    )
}
