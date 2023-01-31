package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    val id: Int,
    val wave: String,
    val wwise: String
)
