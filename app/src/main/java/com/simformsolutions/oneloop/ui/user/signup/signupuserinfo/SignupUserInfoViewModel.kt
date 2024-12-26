package com.simformsolutions.oneloop.ui.user.signup.signupuserinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simformsolutions.oneloop.domain.model.SignUpUserData
import com.simformsolutions.oneloop.ui.user.navigation.Login
import com.simformsolutions.oneloop.ui.user.navigation.SignupPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [SignupUserInfoScreen].
 *
 * Manages user data input, including first name, last name, email, and avatar selection.
 *
 * @param navigator The [Navigator] instance used for navigation.
 */
@HiltViewModel
class SignupUserInfoViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())

    // Public state exposed for the UI layer
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Provides the default UI state with initial values.
     */
    private fun getDefaultUiState(): SignupUserInfoUIState = SignupUserInfoUIState()

    /**
     * Called when the user presses back. Navigates up in the navigation stack.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    /**
     * Updates the email when the user changes the email input.
     *
     * @param email The new email input from the user.
     */
    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                isEmailChanged = true
            )
        }
    }

    /**
     * Updates the first name when the user changes the first name input.
     *
     * @param firstName The new first name input from the user.
     */
    fun onFirstNameChange(firstName: String) {
        _uiState.update {
            it.copy(
                firstName = firstName,
                isFirstNameChanged = true
            )
        }
    }

    /**
     * Updates the last name when the user changes the last name input.
     *
     * @param lastName The new last name input from the user.
     */
    fun onLastNameChange(lastName: String) {
        _uiState.update {
            it.copy(
                lastName = lastName,
                isLastNameChanged = true
            )
        }
    }

    /**
     * Called when the user clicks 'Next' on the user info page.
     * Validates the input and navigates to the password setup screen.
     */
    fun onUserInfoPageNextClicked() {
        _uiState.update {
            it.copy(
                isUserInfoSubmitted = true
            )
        }

        // If all inputs are valid, navigate to the next screen
        if (uiState.value.isValidInputOfUserInfoPage) {
            val signupUserData = SignUpUserData(
                userInfo = SignUpUserData.UserInfo(
                    firstName = uiState.value.firstName,
                    lastName = uiState.value.lastName,
                    email = uiState.value.email,
                    avatar = uiState.value.avatar
                )
            )
            navigator.navigate(SignupPassword(signupUserData))
        }
    }

    /**
     * Called when the user wants to pick an image for the avatar.
     * TODO: Implement the logic for image picking.
     */
    fun pickImage() {
        // Placeholder for the image picking logic
    }

    /**
     * Called when the user clicks 'Sign In' to navigate to the login screen.
     */
    fun onSignInClick() {
        navigator.navigateAndClearBackStack(Login)
    }
}
