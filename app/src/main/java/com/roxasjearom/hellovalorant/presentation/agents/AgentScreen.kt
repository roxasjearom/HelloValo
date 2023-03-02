package com.roxasjearom.hellovalorant.presentation.agents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roxasjearom.hellovalorant.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentScreen(viewModel: AgentViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.agents_title))
                }
            )
        },
        ) { padding ->
        AgentGridList(
            agents = viewModel.agentUiState.collectAsState().value.agents,
            contentPadding = padding,
        )
    }

}
