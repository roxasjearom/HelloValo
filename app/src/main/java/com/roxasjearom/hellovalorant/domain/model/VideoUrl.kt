package com.roxasjearom.hellovalorant.domain.model

data class AbilityUrl(
    val uuid: String,
    val videoUrls: List<VideoUrl>
)

data class VideoUrl(
    val slot: String,
    val videoUrl: String,
)
