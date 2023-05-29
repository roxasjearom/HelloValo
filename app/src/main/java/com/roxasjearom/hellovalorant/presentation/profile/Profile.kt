package com.roxasjearom.hellovalorant.presentation.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.roxasjearom.hellovalorant.data.remote.response.Role
import com.roxasjearom.hellovalorant.ui.theme.HelloValoTheme

@Composable
fun RoleSection(role: Role) {
    Column {
        Text(
            text = stringResource(R.string.role).uppercase(),
            style = MaterialTheme.typography.titleMedium
        )
        Row {
            Text(
                text = role.displayName.uppercase(),
                style = MaterialTheme.typography.headlineMedium,
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(role.displayIcon)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                placeholder = painterResource(R.drawable.ic_initiator),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .align(CenterVertically)
            )
        }
    }
}

@Composable
fun BiographySection(description: String) {
    Column {
        Text(
            text = stringResource(R.string.biography).uppercase(),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RoleSectionPreview() {
    HelloValoTheme {
        RoleSection(
            role = Role(
                assetPath = "",
                description = "",
                displayIcon = "https://media.valorant-api.com/agents/roles/1b47567f-8f7b-444b-aae3-b0c634622d10/displayicon.png",
                "Initiator",
                uuid = ""
            )
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BiographySectionPreview() {
    HelloValoTheme {
        BiographySection(description = "Gekko the Angeleno leads a tight-knit crew of calamitous creatures. His buddies bound forward, scattering enemies out of the way, with Gekko chasing them down to regroup and go again.")
    }
}
