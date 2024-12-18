package com.simformsolutions.oneloop.ui.user.forgotpassword

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simformsolutions.oneloop.R
import com.simformsolutions.oneloop.domain.model.User

/**
 * Defines ui state for [ForgotPasswordScreen]
 *
 * @param email The email of the user
 */
@Stable
@Immutable
data class ForgotPasswordUIState(
    val email: String = ""
) {
    @get:StringRes
    val isValidEmailError: Int
        get() = if (email.isEmpty()) {
            R.string.email_is_required_validation
        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            R.string.enter_valid_email_address_validation
        } else {
            0
        }

    val isValidInput: Boolean
        get() = isValidEmail()

    private fun isValidEmail(): Boolean = isValidEmailError == 0;
}