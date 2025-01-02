package com.simformsolutions.oneloop.ui.user.navigation

import com.simformsolutions.oneloop.domain.model.SignUpUserData
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Serializable
data object Login

@Serializable
data object ForgotPassword

@Serializable
data object SignupUserInfo

@Serializable
data class SignupPassword(
    val signupUserData: SignUpUserData
)

@Serializable
data class SignupPhoneVerification(
    val signupUserData: SignUpUserData
)

@Serializable
data object SelectCountryCode {
    @Serializable
    data class Result(
        val selectedCountryCode: String,
    ) {
        companion object {
            const val KEY = "SELECTED_COUNTRY_CODE_RESULT_KEY"
        }
    }
}

@Serializable
data class SignupOtpVerification(
    val signupUserData: SignUpUserData
)
