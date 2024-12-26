package com.simformsolutions.oneloop.ui.user.login

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simformsolutions.oneloop.R

/**
 * Defines ui state for [LoginScreen]
 *
 * @param email The email of the user
 * @param password The password of the user
 */
@Stable
@Immutable
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isEmailChanged: Boolean = false, // Track if the email field has been changed
    val isPasswordChanged: Boolean = false, // Track if the email field has been changed
    val isSubmitted: Boolean = false // Track if the form has been submitted
) {
    @get:StringRes
    val isValidEmailError: Int
        get() = when {
            !isEmailChanged && !isSubmitted -> {
                0
            }
            email.isEmpty() -> {
                R.string.email_is_required_validation
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                R.string.enter_valid_email_address_validation
            }
            else -> {
                0
            }
        }

    @get:StringRes
    val isValidPasswordError: Int
        get() = when {
            !isPasswordChanged && !isSubmitted -> {
                0
            }
            password.isEmpty() -> {
                R.string.password_is_required_validation
            }
            else -> {
                0
            }
        }

    val isValidInput: Boolean
        get() = isValidEmail() && isValidPassword()

    private fun isValidEmail(): Boolean = isValidEmailError == 0

    private fun isValidPassword(): Boolean = isValidPasswordError == 0
}
