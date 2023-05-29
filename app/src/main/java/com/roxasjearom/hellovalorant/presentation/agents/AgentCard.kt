package com.roxasjearom.hellovalorant.presentation.agents

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.hellovalorant.R
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun AgentGridList(
    agents: List<Agent>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    onAgentClicked: (uuid: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(agents) { agent ->
            AgentCard(agent = agent, onAgentClicked = onAgentClicked)
        }
    }
}

@Composable
fun AgentCard(agent: Agent, onAgentClicked: (uuid: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onAgentClicked(agent.uuid) },
    ) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(agent.background)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    placeholder = painterResource(R.drawable.brim_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .width(182.dp)
                        .height(248.dp)
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(agent.fullPortrait)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .width(182.dp)
                        .height(248.dp)
                )
            }
            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AgentCardPreview() {
    HelloValoTheme {
        AgentCard(
            agent = Agent(
                uuid = "9f0d8ba9-4140-b941-57d3-a7ad57c6b417",
                displayName = "Brimstone",
                fullPortrait = "https://media.valorant-api.com/agents/9f0d8ba9-4140-b941-57d3-a7ad57c6b417/fullportrait.png",
                background = "https://media.valorant-api.com/agents/9f0d8ba9-4140-b941-57d3-a7ad57c6b417/background.png"
            ),
            onAgentClicked = {}
        )
    }
}
