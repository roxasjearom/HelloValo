package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentDataResponse(
    val status: Int,
    val data: List<AgentDto>,
)
