package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentsResponse(
    val status: Int,
    val data: List<AgentDto>,
)
