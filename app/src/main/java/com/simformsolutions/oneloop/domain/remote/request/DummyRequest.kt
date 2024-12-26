package com.simformsolutions.oneloop.domain.remote.request

import kotlinx.serialization.SerialName

// TODO : Remove this when real request models available
data class DummyRequest(
    @SerialName("name")
    val name: String = "",

    @SerialName("location")
    val location: String = ""
)
