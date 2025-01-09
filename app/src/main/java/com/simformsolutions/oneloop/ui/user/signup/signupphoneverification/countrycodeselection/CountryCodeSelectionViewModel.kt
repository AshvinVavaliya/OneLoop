package com.simformsolutions.oneloop.ui.user.signup.signupphoneverification.countrycodeselection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import com.simform.navigation.navigateUpWithResult
import com.simformsolutions.oneloop.ui.user.navigation.SelectCountryCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * The ViewModel for [CountryCodeSelectionScreen].
 *
 * Manages phone number verification with country code selection.
 *
 * @param navigator The [Navigator] instance used for navigation.
 */
@HiltViewModel
class CountryCodeSelectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val navigator: Navigator
) : ViewModel() {

    // UI state with lazy initialization
    private val _uiState = MutableStateFlow(CountryCodeSelectionUIState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CountryCodeSelectionUIState()
    )

    /**
     * Function to update the selected country code.
     *
     * @param selectedCountryCode The new country code selected by the user.
     */
    fun onCountryCodeSelect(selectedCountryCode: String) {
        navigator.navigateUpWithResult(
            key = SelectCountryCode.Result.KEY,
            result = SelectCountryCode.Result(
                selectedCountryCode = selectedCountryCode
            )
        )
    }

    fun onCloseClicked() {
        navigator.navigateUp()
    }
}
