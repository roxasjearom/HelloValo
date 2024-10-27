package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AbilityDto(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String
)
