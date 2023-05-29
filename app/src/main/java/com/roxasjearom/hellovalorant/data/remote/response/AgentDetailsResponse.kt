package com.roxasjearom.hellovalorant.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentDetailsResponse(
    val status: Int,
    val data: AgentDto,
)
