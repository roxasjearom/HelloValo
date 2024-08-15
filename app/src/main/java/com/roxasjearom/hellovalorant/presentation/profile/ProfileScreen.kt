package com.roxasjearom.hellovalorant.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.Role

@Composable
fun ProfileScreen(agentUiState: AgentDetailsUiState) {
    val agentDetails = agentUiState.agentDetails
    agentDetails?.let {
        Box(modifier = Modifier.fillMaxSize()) {

            Text(
                text = agentDetails.displayName.uppercase(),
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.30f),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 72.sp,
                maxLines = 1,
                modifier = Modifier.padding(16.dp),
            )
            Text(
                text = agentDetails.displayName.uppercase(),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agentDetails.fullPortrait)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier.fillMaxHeight()
            )

            AgentDescriptionSection(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomCenter),
                agentDetails = agentDetails,
            )
        }
    }
}

@Composable
fun AgentDescriptionSection(
    modifier: Modifier = Modifier,
    agentDetails: AgentDetails,
) {
    Surface(
        color = Color.Black.copy(alpha = 0.25f),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
        ) {
            RoleSection(role = agentDetails.role)
            Spacer(modifier = Modifier.height(16.dp))
            BiographySection(description = agentDetails.description)
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
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
            )
        )
    )
}
