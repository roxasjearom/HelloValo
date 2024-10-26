package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.roxasjearom.hellovalorant.data.remote.response.Ability
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun AbilitiesPage(
    modifier: Modifier = Modifier,
    abilities: List<Ability>,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.special_abilities).uppercase(),
                style = MaterialTheme.typography.displayMedium,
                )
            AbilitiesSection(abilities = abilities)
        }
    }
}

@Composable
fun AbilitiesSection(
    modifier: Modifier = Modifier,
    abilities: List<Ability>,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        abilities.forEach { ability ->
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
                    .width(48.dp)
                    .height(48.dp)
                    .padding(8.dp)
                    .align(CenterVertically)
            )
        }
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
            )
        )
    }
}
