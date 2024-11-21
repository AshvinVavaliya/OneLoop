package com.simformsolutions.oneloop.ui.user.forgotpassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simformsolutions.oneloop.common.dispatcher.IoDispatcher
import com.simformsolutions.oneloop.domain.model.User
import com.simformsolutions.oneloop.ui.user.navigation.ForgotPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [ForgotPassword]
 *
 * @param ioDispatcher The IO coroutine dispatcher
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())

    val uiState = _uiState.stateIn(
        scope = viewModelScope, started = SharingStarted.Lazily, initialValue = getDefaultUiState()
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
                    email = email
                )
            }
    }

    // Function to validate the email
    fun validateEmail(email: String) {
        if (uiState.value.isValidInput) {
            //navigator.navigate(ProductRoute)
        }
    }

    private fun getDefaultUiState(): ForgotPasswordUIState = ForgotPasswordUIState()
}