package com.roxasjearom.hellovalorant.data.mapper

import com.roxasjearom.hellovalorant.data.remote.response.AbilityUrlDto
import com.roxasjearom.hellovalorant.data.remote.response.VideoUrlDto
import com.roxasjearom.hellovalorant.domain.model.AbilityUrl
import com.roxasjearom.hellovalorant.domain.model.VideoUrl

fun VideoUrlDto.toVideoUrl() = VideoUrl(
    slot = slot,
    videoUrl = videoUrl,
)

fun AbilityUrlDto.toAbilityUrl() = AbilityUrl(
    uuid = uuid,
    videoUrls = abilityUrls.map { it.toVideoUrl() },
)
