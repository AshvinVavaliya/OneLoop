package com.simformsolutions.oneloop.ui.user.signup.signupoptverification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simformsolutions.oneloop.ui.user.navigation.SignupOtpVerification
import com.simformsolutions.oneloop.ui.user.navigation.UserWalkthrough
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [SignupOtpVerificationScreen].
 *
 * Handles the logic for OTP input and verification.
 *
 * @param navigator The [Navigator] instance used for navigation.
 */
@HiltViewModel
class SignupOtpVerificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
) : ViewModel() {

    private val signupOTPVerification: SignupOtpVerification =
        requireNotNull(savedStateHandle.getNavArgs<SignupOtpVerification>())

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Handles the back button click and navigates up.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    /**
     * Handles changes to the OTP input fields.
     *
     * @param index Index of the OTP field being modified.
     * @param value The new value entered by the user.
     */
    fun onOtpCodeChange(value: String) {
        _uiState.update {
            it.copy(
                otpValues = value
            )
        }
    }

    /**
     * Handles OTP verification button click.
     */
    fun onOtpVerifyButtonClick() {
        navigator.navigate(UserWalkthrough)
    }

    fun onReSendClick() {
        // Resend OTP
    }

    /**
     * Provides the default UI state for OTP verification.
     */
    private fun getDefaultUiState(): SignupOtpVerificationUIState = SignupOtpVerificationUIState()
}
