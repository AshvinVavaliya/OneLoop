package com.simformsolutions.oneloop.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing the user data for sign-up.
 */
@Serializable
data class SignUpUserData(
    @SerialName("userInfo")
    val userInfo: UserInfo = UserInfo(),
    @SerialName("userPassword")
    val userPassword: UserPassword = UserPassword(),
    @SerialName("userPhoneVerification")
    val userPhoneVerification: UserPhoneVerification = UserPhoneVerification(),
) {
    /**
     * Nested data class representing user info (name, email, avatar).
     */
    @Serializable
    data class UserInfo(
        @SerialName("firstName")
        val firstName: String = "",
        @SerialName("lastName")
        val lastName: String = "",
        @SerialName("email")
        val email: String = "",
        @SerialName("avatar")
        val avatar: String = ""
    )

    /**
     * Nested data class representing user password and confirmation.
     */
    @Serializable
    data class UserPassword(
        @SerialName("password")
        val password: String = "",
        @SerialName("confirmPassword")
        val confirmPassword: String = ""
    )

    /**
     * Nested data class representing phone verification details.
     */
    @Serializable
    data class UserPhoneVerification(
        @SerialName("countryCode")
        val countryCode: String = "+1",
        @SerialName("phoneNumber")
        val phoneNumber: String = ""
    )
}
