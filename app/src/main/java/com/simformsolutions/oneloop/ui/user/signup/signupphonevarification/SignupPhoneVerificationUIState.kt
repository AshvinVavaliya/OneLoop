package com.simformsolutions.oneloop.ui.user.signup.signupphonevarification

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.core.text.isDigitsOnly
import com.simformsolutions.oneloop.R

/**
 * Defines ui state for [SignupPhoneVerificationPageScreen]
 * @param countryCode The countryCode of the user
 * @param phoneNumber The phoneNumber of the user
 *
 */
@Stable
@Immutable
data class SignupPhoneVerificationUIState(
    val countryCode: String = "+1",
    val phoneNumber: String = "",
    val countryCodeList: List<String> = listOf("+1", "+44", "+91", "+61", "+81", "+49"), // List of country codes (It will update in future)
    val isCountryCodeSelected: Boolean = true,
    val isPhoneNumberChanged: Boolean = false,
    val isPhoneNumberVerificationPageSubmitted: Boolean = false
) {
    @get:StringRes
    val isValidPhoneNumberError: Int
        get() = when {
            !isCountryCodeSelected && !isPhoneNumberVerificationPageSubmitted -> {
                0
            }
            !isPhoneNumberChanged && !isPhoneNumberVerificationPageSubmitted -> {
                0
            }
            countryCode.isEmpty() -> {
                R.string.please_select_country_code
            }
            phoneNumber.isEmpty() -> {
                R.string.phone_number_is_required
            }
            !(phoneNumber.isDigitsOnly() && phoneNumber.count() in 10..13) -> {
                R.string.enter_a_valid_phone_number
            }
            else -> {
                0
            }
        }

    val isValidInputOfPhoneNumberVerificationPage: Boolean
        get() = isValidPhoneNumber()

    private fun isValidPhoneNumber(): Boolean = isValidPhoneNumberError == 0
}
