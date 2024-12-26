package com.simformsolutions.oneloop.ui.user.signup.signupphonevarification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simformsolutions.oneloop.ui.user.navigation.SignupOtpVerification
import com.simformsolutions.oneloop.ui.user.navigation.SignupPhoneVerification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [SignupPhoneVerificationScreen].
 *
 * Manages phone number verification with country code selection.
 *
 * @param navigator The [Navigator] instance used for navigation.
 */
@HiltViewModel
class SignupPhoneVerificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    private val signupPhoneVerification: SignupPhoneVerification = requireNotNull(savedStateHandle.getNavArgs<SignupPhoneVerification>())
    private val _countryCode = mutableStateOf("+1")  // Default country code
    private val _isDialogVisible = mutableStateOf(false)  // Dialog visibility for country code selection
    val isDialogVisible: State<Boolean> get() = _isDialogVisible  // Exposed state for dialog visibility

    // UI state with lazy initialization
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Called when users press back. Navigates up in the navigation stack.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    /**
     * Function to show the dialog for selecting a country code.
     */
    fun selectCountryCode() {
        _isDialogVisible.value = true
    }

    /**
     * Function to update the selected country code.
     *
     * @param newCode The new country code selected by the user.
     */
    fun updateCountryCode(newCode: String) {
        _uiState.update {
            it.copy(
                countryCode = newCode,
                isCountryCodeSelected = true
            )
        }
        _countryCode.value = newCode
        _isDialogVisible.value = false  // Close the dialog after updating
    }

    /**
     * Called when the user clicks the 'Next' button on the phone verification page.
     * Validates the inputs and navigates to the OTP verification step.
     */
    fun onPhoneNumberVerificationPageNextClicked() {
        _uiState.update {
            it.copy(
                isPhoneNumberVerificationPageSubmitted = true
            )
        }
        if (uiState.value.isValidInputOfPhoneNumberVerificationPage) {
            val updatedSignUpUserData = signupPhoneVerification.signupUserData.copy(
                userPhoneVerification = signupPhoneVerification.signupUserData.userPhoneVerification.copy(
                    countryCode = uiState.value.countryCode,
                    phoneNumber = uiState.value.phoneNumber
                )
            )
            navigator.navigate(
                SignupOtpVerification(
                    signupUserData = updatedSignUpUserData
                )
            )
        }
    }

    /**
     * Updates the phone number when the user types in the phone number field.
     *
     * @param phoneNumber The new phone number input.
     */
    fun onPhoneNumberChange(phoneNumber: String) {
        _uiState.update {
            it.copy(
                phoneNumber = phoneNumber,
                isPhoneNumberChanged = true
            )
        }
    }

    /**
     * Provides the default UI state for the phone verification page.
     */
    private fun getDefaultUiState(): SignupPhoneVerificationUIState = SignupPhoneVerificationUIState()
}
