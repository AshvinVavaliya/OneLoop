package com.simformsolutions.oneloop.ui.user.signup.signupuserinfo

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simformsolutions.oneloop.R

/**
 *
 * Defines ui state for [SignupUserInfoScreen]
 * @param firstName The firstName of the user
 * @param lastName The lastName of the user
 * @param email The email of the user
 * @param avatar The avatar of the user
 *
 */
@Stable
@Immutable
data class SignupUserInfoUIState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val avatar: String = "",
    val isFirstNameChanged: Boolean = false,
    val isLastNameChanged: Boolean = false,
    val isEmailChanged: Boolean = false,
    val isUserInfoSubmitted: Boolean = false,
) {
    // UserInfoScreen UI state
    @get:StringRes
    val isValidEmailError: Int
        get() = when {
            !isEmailChanged && !isUserInfoSubmitted -> {
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
    val isValidFirstNameError: Int
        get() = when {
            !isFirstNameChanged && !isUserInfoSubmitted -> {
                0
            }
            firstName.isEmpty() -> {
                R.string.first_name_required
            }
            else -> {
                0
            }
        }

    @get:StringRes
    val isValidLastNameError: Int
        get() = when {
            !isLastNameChanged && !isUserInfoSubmitted -> {
                0
            }
            lastName.isEmpty() -> {
                R.string.last_name_required
            }
            else -> {
                0
            }
        }

    val isValidInputOfUserInfoPage: Boolean
        get() = isValidEmail() && isValidFirstName() && isValidLastName()

    private fun isValidEmail(): Boolean = isValidEmailError == 0

    private fun isValidFirstName(): Boolean = isValidFirstNameError == 0

    private fun isValidLastName(): Boolean = isValidFirstNameError == 0
}
