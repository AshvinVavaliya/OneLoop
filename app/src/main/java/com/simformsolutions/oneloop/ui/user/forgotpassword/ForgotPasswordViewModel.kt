package com.simformsolutions.oneloop.ui.user.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simformsolutions.oneloop.ui.user.navigation.ForgotPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [ForgotPassword]
 *
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Called when users presses back.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                isEmailChanged = true
            )
        }
    }

    fun forgotPasswordClicked() {
        _uiState.update {
            it.copy(
                isSubmitted = true
            )
        }
        if (uiState.value.isValidInput) {
            //navigator.navigate(ProductRoute)
        }
    }

    private fun getDefaultUiState(): ForgotPasswordUIState = ForgotPasswordUIState()
}