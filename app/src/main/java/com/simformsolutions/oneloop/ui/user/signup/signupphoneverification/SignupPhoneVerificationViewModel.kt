package com.simformsolutions.oneloop.ui.user.signup.signupphoneverification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simform.navigation.getResult
import com.simformsolutions.oneloop.ui.user.navigation.SignupOtpVerification
import com.simformsolutions.oneloop.ui.user.navigation.SelectCountryCode
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

    private val signupPhoneVerification: SignupPhoneVerification =
        requireNotNull(savedStateHandle.getNavArgs<SignupPhoneVerification>())

    // UI state with lazy initialization
    private val _uiState = MutableStateFlow(getDefaultUiState())
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
    fun onCountryCodeClick() {
        navigator.getResult<SelectCountryCode.Result>(
            destinationKey = SelectCountryCode.Result.KEY,
            viewModel = this@SignupPhoneVerificationViewModel
        ) {
            if (this == null) return@getResult
            _uiState.update {
                it.copy(
                    countryCode = this.selectedCountryCode,
                    isCountryCodeSelected = true
                )
            }
        }
        navigator.navigate(SelectCountryCode)
    }

    /**
     * Called when the user clicks the 'Next' button on the phone verification page.
     * Validates the inputs and navigates to the OTP verification step.
     */
    fun onNextClicked() {
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
    private fun getDefaultUiState(): SignupPhoneVerificationUIState =
        SignupPhoneVerificationUIState()
}
