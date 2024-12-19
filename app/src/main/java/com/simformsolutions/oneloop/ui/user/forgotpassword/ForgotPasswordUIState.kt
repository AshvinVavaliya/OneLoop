package com.simformsolutions.oneloop.ui.user.forgotpassword

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simformsolutions.oneloop.R

/**
 * Defines ui state for [ForgotPasswordScreen]
 *
 * @param email The email of the user
 */
@Stable
@Immutable
data class ForgotPasswordUIState(
    val email: String = "",
    val isEmailChanged: Boolean = false, // Track if the email field has been changed
    val isSubmitted: Boolean = false // Track if the form has been submitted
) {
    @get:StringRes
    val isValidEmailError: Int
        get() = if (!isEmailChanged && !isSubmitted) {
            0
        } else if (email.isEmpty()) {
            R.string.email_is_required_validation
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            R.string.enter_valid_email_address_validation
        } else {
            0
        }

    val isValidInput: Boolean
        get() = isValidEmail()

    private fun isValidEmail(): Boolean = isValidEmailError == 0
}