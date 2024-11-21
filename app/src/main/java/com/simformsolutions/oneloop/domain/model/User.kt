package com.simformsolutions.oneloop.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User API response.
 */
@Serializable
data class User(
    @SerialName("login")
    val login: Login = Login()
) {
    @Serializable
    data class Login(
        @SerialName("email")
        val email: String = "",
        @SerialName("password")
        val password: String = "",
        val emailValidation: String = ""
    )
}
