package com.simformsolutions.oneloop.ui.user.signup.signupoptverification

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 *
 * Defines ui state for [SignupOtpVerificationScreen]
 *
 */
@Stable
@Immutable
data class SignupOtpVerificationUIState(
    val otpValues: String = "",
    val focusedIndex: Int = 0,
) {
    // do validation related stuff
}
