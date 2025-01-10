package com.simformsolutions.oneloop.ui.user.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simformsolutions.oneloop.ui.user.navigation.ForgotPassword
import com.simformsolutions.oneloop.ui.user.navigation.SignupUserInfo
import com.simformsolutions.oneloop.ui.user.navigation.UserWalkthrough
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [LoginScreen]
 *
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    fun onForgotPasswordClick() {
        navigator.navigate(ForgotPassword)
    }

    fun onEmailChange(email: String) {
        _uiState.update {
            it.copy(
                email = email,
                isEmailChanged = true
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                isPasswordChanged = true
            )
        }
    }

    fun signInClicked() {
        _uiState.update {
            it.copy(
                isSubmitted = true
            )
        }
        if (uiState.value.isValidInput) {
            navigator.navigate(UserWalkthrough)
        }
    }

    fun onSignUpClick() {
        navigator.navigate(SignupUserInfo)
    }

    private fun getDefaultUiState(): LoginUiState = LoginUiState()
}
