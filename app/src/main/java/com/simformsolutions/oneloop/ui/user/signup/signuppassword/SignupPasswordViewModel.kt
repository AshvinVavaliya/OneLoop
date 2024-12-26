package com.simformsolutions.oneloop.ui.user.signup.signuppassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simformsolutions.oneloop.ui.user.navigation.Login
import com.simformsolutions.oneloop.ui.user.navigation.SignupPassword
import com.simformsolutions.oneloop.ui.user.navigation.SignupPhoneVerification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [SignupPasswordScreen].
 *
 * Manages password creation and confirmation.
 *
 * @param navigator The [Navigator] instance used for navigation.
 */
@HiltViewModel
class SignupPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    private val signupPassword: SignupPassword = requireNotNull(savedStateHandle.getNavArgs<SignupPassword>())

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Called when users press back.
     * Navigates up in the navigation stack.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    /**
     * Updates the password when the user types in the password field.
     *
     * @param password The new password input.
     */
    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isPasswordChanged = true
            )
        }
    }

    /**
     * Updates the confirm password when the user types in the confirm password field.
     *
     * @param confirmPassword The new confirm password input.
     */
    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                isConfirmPasswordChanged = true
            )
        }
    }

    /**
     * Called when the user clicks the 'Next' button on the password page.
     * Validates inputs and navigates to the next step (Phone Verification).
     */
    fun onPasswordNextClicked() {
        _uiState.update {
            it.copy(
                isPasswordPageSubmitted = true
            )
        }
        if (uiState.value.isValidInputOfPasswordPage) {
            val updatedSignUpUserData = signupPassword.signupUserData.copy(
                userPassword = signupPassword.signupUserData.userPassword.copy(
                    password = uiState.value.password,
                    confirmPassword = uiState.value.confirmPassword
                )
            )
            navigator.navigate(
                SignupPhoneVerification(
                    signupUserData = updatedSignUpUserData
                )
            )
        }
    }

    /**
     * Provides the default UI state for the password page.
     */
    private fun getDefaultUiState(): SignupPasswordUIState = SignupPasswordUIState()

    /**
     * Called when the user clicks the 'Sign In' button, navigating to the login screen.
     */
    fun onSignInClick() {
        navigator.navigateAndClearBackStack(Login)
    }
}
