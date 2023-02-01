package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AgentScreen(viewModel: AgentViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        AgentGridList(agents = viewModel.agentUiState.collectAsState().value.agents)
    }
}
