package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Role(
    val uuid: String,
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
)
