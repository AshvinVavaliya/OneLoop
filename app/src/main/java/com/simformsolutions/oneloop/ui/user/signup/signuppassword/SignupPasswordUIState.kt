package com.simformsolutions.oneloop.ui.user.signup.signuppassword

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simformsolutions.oneloop.R

/**
 * Defines ui state for [SignupSignupPasswordUIState]
 *
 * @param password The password of the user
 * @param confirmPassword The confirmPassword of the user
 *
 */
@Stable
@Immutable
data class SignupPasswordUIState(
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordChanged: Boolean = false,
    val isConfirmPasswordChanged: Boolean = false,
    val isPasswordPageSubmitted: Boolean = false,
) {
    @get:StringRes
    val isValidPasswordError: Int
        get() = when {
            !isPasswordChanged && !isPasswordPageSubmitted -> {
                0
            }
            password.isEmpty() -> {
                R.string.password_is_required
            }
            password.length < 8 -> {
                R.string.password_must_be_at_least_8_characters
            }
            !password.matches(".*[A-Z].*".toRegex()) -> {
                R.string.password_must_contain_at_least_one_uppercase_letter
            }
            !password.matches(".*[a-z].*".toRegex()) -> {
                R.string.password_must_contain_at_least_one_lowercase_letter
            }
            !password.matches(".*\\d.*".toRegex()) -> {
                R.string.password_must_contain_at_least_one_number
            }
            else -> {
                0
            }
        }

    @get:StringRes
    val isValidConfirmPasswordError: Int
        get() = when {
            !isConfirmPasswordChanged && !isPasswordPageSubmitted -> {
                0
            }
            confirmPassword.isEmpty() -> {
                R.string.confirm_password_is_required
            }
            password != confirmPassword -> {
                R.string.passwords_do_not_match
            }
            else -> {
                0
            }
        }

    val isValidInputOfPasswordPage: Boolean
        get() = isValidPassword() && isValidConfirmPassword()

    private fun isValidPassword(): Boolean = isValidPasswordError == 0

    private fun isValidConfirmPassword(): Boolean = isValidConfirmPasswordError == 0
}
