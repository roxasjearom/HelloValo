package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.Role
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun AgentDetailsScreen(modifier: Modifier = Modifier, agentUiState: AgentDetailsUiState) {
    val agentDetails = agentUiState.agentDetails
    agentDetails?.let {
        Box {
            val pagerState = rememberPagerState(pageCount = { 2 })
            VerticalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize(),
            ) { page ->
                when (page) {
                    0 -> {
                        ProfilePage(agentUiState = agentUiState)
                    }

                    1 -> {
                        AbilitiesPage(
                            abilities = agentUiState.agentDetails.abilities,
                            videoUrls = agentUiState.videoUrls,
                        )
                    }
                }
            }
            Column(
                Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AgentDetailsScreenPreview(modifier: Modifier = Modifier) {
    HelloValoTheme {
        AgentDetailsScreen(
            agentUiState = AgentDetailsUiState(
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
        )
    }
}
