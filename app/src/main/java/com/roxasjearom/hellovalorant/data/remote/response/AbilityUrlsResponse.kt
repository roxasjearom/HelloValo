package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityUrlsResponse(
    val data: List<AbilityUrlDto>
)

@JsonClass(generateAdapter = true)
data class AbilityUrlDto(
    val uuid: String,
    val abilityUrls: List<VideoUrlDto>
)

@JsonClass(generateAdapter = true)
data class VideoUrlDto(
    val slot: String,
    val videoUrl: String,
)
