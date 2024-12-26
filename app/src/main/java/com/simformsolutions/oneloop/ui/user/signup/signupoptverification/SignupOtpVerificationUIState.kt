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
    val countryCodeList: List<String> = listOf("+1", "+44", "+91", "+61", "+81", "+49"), // List of country codes (It will update in future)
    val otpValues: List<String> = List(4) { "" },
    val focusedIndex: Int = 0,
) {
    // do validation related stuff
}
