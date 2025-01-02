package com.simformsolutions.oneloop.ui.user.signup.signupphoneverification.countrycodeselection

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.button.AppButton
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * CountryCodeSelection route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [CountryCodeSelectionViewModel] instance
 */
@Composable
fun CountryCodeSelectionRoute(
    modifier: Modifier,
    viewModel: CountryCodeSelectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CountryCodeSelectionScreen(
        modifier = modifier,
        uiState = uiState,
        onCloseClicked = viewModel::onCloseClicked,
        onCountryCodeSelect = viewModel::onCountryCodeSelect
    )
}

/**
 * Standalone screen to show CountryCodeSelectionPage UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [CountryCodeSelectionUIState]
 */
@Composable
private fun CountryCodeSelectionScreen(
    modifier: Modifier,
    uiState: CountryCodeSelectionUIState,
    onCloseClicked: () -> Unit,
    onCountryCodeSelect: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppTheme.appColorScheme.white)
            .padding(dimensionResource(id = R.dimen.common_space_small)) // Adjust the padding as needed
    ) {
        // Title
        AppText(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.common_space_small)),
            text = "Select Country Code",
            style = AppTheme.appTypography.h6Bold,
            textColor = AppTheme.appColorScheme.blackColor,
        )

        // Country Codes List
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            uiState.countryCodeList.forEach { code ->
                AppText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCountryCodeSelect(code)
                        },
                    text = code,
                    style = AppTheme.appTypography.h6Normal,
                    textColor = AppTheme.appColorScheme.blackColor
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.common_space_small)))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = AppTheme.appColorScheme.textColor),
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.common_space_small)))
            }
        }

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)
                ),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.common_space_medium)),
            onClick = onCloseClicked
        ) {
            AppText(
                text = stringResource(R.string.close_dialog_close_btn_txt),
                textColor = AppTheme.appColorScheme.white,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        CountryCodeSelectionScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = CountryCodeSelectionUIState(),
            onCloseClicked = {},
            onCountryCodeSelect = { _: String -> }
        )
    }
}
