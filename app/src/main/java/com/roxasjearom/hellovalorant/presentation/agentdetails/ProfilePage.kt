package com.roxasjearom.hellovalorant.presentation.agentdetails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.hellovalorant.LocalAnimatedVisibilityScope
import com.roxasjearom.hellovalorant.LocalSharedTransitionScope
import com.roxasjearom.hellovalorant.R
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.model.Role

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProfilePage(modifier: Modifier = Modifier, agentDetails: AgentDetails) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    with(sharedTransitionScope) {
        Box(modifier = Modifier.fillMaxSize()) {
            val gradientColors = agentDetails.backgroundGradientColors
                .map { hexColor -> Color("#$hexColor".toColorInt()) }
            val defaultColors =
                listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)

            val infiniteTransition = rememberInfiniteTransition(label = "background")
            val targetOffset = with(LocalDensity.current) { 1000.dp.toPx() }
            val offset by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = targetOffset,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 25_000,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "offset"
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(40.dp)
                    .drawWithCache {
                        val brushSize = 800f
                        val brush = Brush.linearGradient(
                            colors = gradientColors.ifEmpty { defaultColors },
                            start = Offset(offset, offset),
                            end = Offset(offset + brushSize, offset + brushSize),
                            tileMode = TileMode.Mirror,
                        )
                        onDrawBehind {
                            drawRect(brush)
                        }
                    }
            )

            Text(
                text = agentDetails.displayName.uppercase(),
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.30f),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 72.sp,
                maxLines = 1,
                modifier = modifier.padding(16.dp),
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agentDetails.background)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                placeholder = painterResource(R.drawable.brim_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = agentDetails.displayName.uppercase(),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agentDetails.fullPortrait)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.None,
                modifier = Modifier
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = agentDetails.uuid),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                    .fillMaxSize()
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
    ProfilePage(
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
            backgroundGradientColors = listOf("90e3fdff", "557f8cff", "2b4e7cff", "1e3344ff")
        )

    )
}
