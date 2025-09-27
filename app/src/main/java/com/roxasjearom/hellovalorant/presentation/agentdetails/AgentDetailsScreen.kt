package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.Role
import com.roxasjearom.hellovalorant.presentation.common.FullScreenLoadingIndicator
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun AgentDetailsRoute(viewModel: AgentDetailsViewModel = hiltViewModel()) {
    val uiState by viewModel.agentDetailsUiState.collectAsStateWithLifecycle()
    val agentDetails = uiState.agentDetails

    if (uiState.isLoading) {
        FullScreenLoadingIndicator()
    } else {
        agentDetails?.let {
            AgentDetailsScreen(agentDetails = it)
        }
    }
}

@Composable
fun AgentDetailsScreen(modifier: Modifier = Modifier, agentDetails: AgentDetails) {
    Scaffold { contentPadding ->
        ProfilePage(modifier = modifier.padding(contentPadding), agentDetails = agentDetails)
    }
}

@Preview
@Composable
fun AgentDetailsScreenPreview() {
    HelloValoTheme {
        AgentDetailsScreen(
            agentDetails = AgentDetails(
                uuid = "e370fa57-4757-3604-3648-499e1f642d3f",
                abilities = emptyList(),
                background = "https://media.valorant-api.com/agents/e370fa57-4757-3604-3648-499e1f642d3f/background.png",
                description = "Joining from the USA, Brimstone's orbital arsenal ensures his squad always has the advantage. His ability to deliver utility precisely and safely make him the unmatched boots-on-the-ground commander.",
                displayIcon = "",
                displayName = "Brimstone",
                fullPortrait = "https://media.valorant-api.com/agents/e370fa57-4757-3604-3648-499e1f642d3f/fullportrait.png",
                role = Role(
                    uuid = "123",
                    description = "Controllers are experts in slicing up dangerous territory to set their team up for success.",
                    displayName = "Controller",
                    displayIcon = "",
                ),
                backgroundGradientColors = listOf(
                    "90e3fdff",
                    "557f8cff",
                    "2b4e7cff",
                    "1e3344ff"
                )
            )
        )
    }
}
