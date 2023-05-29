package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.runtime.Composable
import com.roxasjearom.hellovalorant.domain.model.Agent

@Composable
fun AgentScreen(agents: List<Agent>, onAgentClicked: (String) -> Unit) {
    AgentGridList(
        agents = agents,
        onAgentClicked = onAgentClicked,
    )
}
