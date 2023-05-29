package com.roxasjearom.hellovalorant.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roxasjearom.hellovalorant.R

@Composable
fun ProfileScreen(agentUiState: AgentDetailsUiState) {
    /*val agentUiState: AgentDetailsUiState =
        viewModel.agentDetailsUiState.collectAsStateWithLifecycle().value*/

    Column(modifier = Modifier.fillMaxSize()) {
        val agentDetails = agentUiState.agentDetails
        agentDetails?.let {
            RoleSection(role = it.role)
            BiographySection(description = it.description)
        }
    }
}
