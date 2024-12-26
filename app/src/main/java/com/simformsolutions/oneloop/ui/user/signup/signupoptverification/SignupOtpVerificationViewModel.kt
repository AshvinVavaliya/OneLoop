package com.simformsolutions.oneloop.ui.user.signup.signupoptverification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simformsolutions.oneloop.ui.user.navigation.SignupOtpVerification
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

    private val _uiState = MutableStateFlow(getDefaultUiState())
    private val signupOTPVerification: SignupOtpVerification =
        requireNotNull(savedStateHandle.getNavArgs<SignupOtpVerification>())

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
    fun onOtpCodeChanged(index: Int, value: String) {
        if (value.length <= 1) {
            val newOtpValues = _uiState.value.otpValues.toMutableList()
            newOtpValues[index] = value
            _uiState.update {
                it.copy(
                    otpValues = newOtpValues,
                    focusedIndex = index
                )
            }
            moveToNextField(index, value)
        }
    }

    /**
     * Moves the focus to the next or previous OTP input field.
     *
     * @param index Current field index.
     * @param value The value of the current OTP field.
     */
    private fun moveToNextField(index: Int, value: String) {
        val otpValues = _uiState.value.otpValues
        when {
            value.isNotEmpty() && index < otpValues.size - 1 -> {
                _uiState.update {
                    it.copy(
                        focusedIndex = index + 1
                    )
                }
            }
            value.isEmpty() && index > 0 -> {
                _uiState.update {
                    it.copy(
                        focusedIndex = index - 1
                    )
                }
            }
        }
    }

    /**
     * Handles OTP verification button click.
     */
    fun onOtpVerifyButtonClicked() {
        // Navigate to Walkthrough screen or perform OTP verification
    }

    /**
     * Provides the default UI state for OTP verification.
     */
    private fun getDefaultUiState(): SignupOtpVerificationUIState = SignupOtpVerificationUIState()
}
