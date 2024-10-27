package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.hellovalorant.R
import com.roxasjearom.hellovalorant.domain.model.Ability
import com.roxasjearom.hellovalorant.domain.model.VideoUrl
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun AbilitiesPage(
    modifier: Modifier = Modifier,
    abilities: List<Ability>,
    videoUrls: List<VideoUrl>,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        AbilitiesSection(
            abilities = abilities.filterNot { it.slot.equals("Passive", true) },
            videoUrls = videoUrls,
        )
    }
}

@Composable
fun AbilitiesSection(
    modifier: Modifier = Modifier,
    abilities: List<Ability>,
    videoUrls: List<VideoUrl>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally,
    ) {
        var selectedPosition by remember { mutableIntStateOf(0) }

        Text(
            text = stringResource(R.string.special_abilities).uppercase(),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (videoUrls.isNotEmpty()) {
            VideoPlayer(videoUrl = videoUrls[selectedPosition].videoUrl)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            abilities.forEachIndexed { index, ability ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ability.displayIcon)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_initiator),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .align(CenterVertically)
                        .clickable {
                            selectedPosition = index
                        }
                        .padding(12.dp),
                    alpha = if (index == selectedPosition) 1f else 0.3f
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = abilities[selectedPosition].displayName.uppercase(),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = abilities[selectedPosition].description,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun AbilitiesScreenPreview(modifier: Modifier = Modifier) {
    HelloValoTheme {
        AbilitiesPage(
            abilities = listOf(
                Ability(
                    description = "INSTANTLY propel Jett high into the air.",
                    displayIcon = "https://media.valorant-api.com/agents/add6443a-41bd-e414-f6ad-e58d267f4e95/abilities/ability1/displayicon.png",
                    displayName = "Updraft",
                    slot = "Ability1",
                ),
                Ability(
                    description = "INSTANTLY propel Jett high into the air.",
                    displayIcon = "https://media.valorant-api.com/agents/add6443a-41bd-e414-f6ad-e58d267f4e95/abilities/ability1/displayicon.png",
                    displayName = "Updraft",
                    slot = "Ability1",
                ),
                Ability(
                    description = "INSTANTLY propel Jett high into the air.",
                    displayIcon = "https://media.valorant-api.com/agents/add6443a-41bd-e414-f6ad-e58d267f4e95/abilities/ability1/displayicon.png",
                    displayName = "Updraft",
                    slot = "Ability1",
                ),
                Ability(
                    description = "INSTANTLY propel Jett high into the air.",
                    displayIcon = "https://media.valorant-api.com/agents/add6443a-41bd-e414-f6ad-e58d267f4e95/abilities/ability1/displayicon.png",
                    displayName = "Updraft",
                    slot = "Ability1",
                ),
            ),
            videoUrls = emptyList(),
        )
    }
}
