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
data class SignupOtpVerification(
    val signupUserData: SignUpUserData
)
