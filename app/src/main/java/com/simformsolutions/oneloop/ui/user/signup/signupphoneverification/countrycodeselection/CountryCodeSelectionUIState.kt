package com.simformsolutions.oneloop.ui.user.signup.signupphoneverification.countrycodeselection

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * Defines ui state for [CountryCodeSelectionPageScreen]
 *
 */
@Stable
@Immutable
data class CountryCodeSelectionUIState(
    val countryCodeList: List<String> = listOf(
        "+1",
        "+44",
        "+91",
        "+61",
        "+81",
        "+49"
    ), // List of country codes (It will update in future)
)