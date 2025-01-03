package com.simformsolutions.oneloop.ui.user.signup.signupphoneverification

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.appbar.CommonTopAppBar
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.scaffold.AppScaffold
import com.simform.design.text.AppText
import com.simform.design.textfield.AppUnderlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * SignupPhoneVerification route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [SignupPhoneVerificationViewModel] instance
 */
@Composable
fun SignupPhoneVerificationRoute(
    modifier: Modifier,
    viewModel: SignupPhoneVerificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignupPhoneVerificationScreen(
        modifier = modifier,
        uiState = uiState,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        onCountryCodeClick = viewModel::onCountryCodeClick,
        onNextClicked = viewModel::onNextClicked,
        onBackClick = viewModel::onBackClick
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

@Composable
private fun SignupPhoneVerificationScreen(
    modifier: Modifier,
    uiState: SignupPhoneVerificationUIState,
    onPhoneNumberChange: (String) -> Unit,
    onCountryCodeClick: () -> Unit,
    onNextClicked: () -> Unit,
    onBackClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            CommonTopAppBar(
                title = stringResource(R.string.verification),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.img_login_bg),
                    contentScale = ContentScale.FillBounds
                )
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(
                    start = dimensionResource(id = R.dimen.main_contains_side_space),
                    end = dimensionResource(id = R.dimen.main_contains_side_space)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AppIcon(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)),
                painter = painterResource(id = R.drawable.icon_smartphone)
            )

            AppUnderlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.phoneNumber,
                textStyle = AppTheme.appTypography.body1Normal.copy(color = AppTheme.appColorScheme.white),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.phone_number),
                        textColor = AppTheme.appColorScheme.textColor
                    )
                },
                supportingText = {
                    if (uiState.isValidPhoneNumberError > 0) {
                        AppText(
                            text = stringResource(uiState.isValidPhoneNumberError),
                            textColor = AppTheme.appColorScheme.error
                        )
                    }
                },
                onValueChange = onPhoneNumberChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AppIcon(
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.common_space_very_small)),
                            painter = painterResource(id = R.drawable.icon_phone)
                        )

                        Row(
                            modifier = Modifier
                                .clickable { onCountryCodeClick() },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AppText(
                                text = uiState.countryCode.ifEmpty { stringResource(R.string.select_country_code) },
                                textColor = AppTheme.appColorScheme.textColor
                            )
                            AppIcon(
                                painter = painterResource(id = R.drawable.icon_dropbox),
                                tint = AppTheme.appColorScheme.textColor
                            )

                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.common_space_very_small)))
                        }
                    }
                }
            )

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.common_space_medium)),
                enabled = uiState.isValidInputOfPhoneNumberVerificationPage,
                onClick = onNextClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_next),
                    textColor = AppTheme.appColorScheme.white
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        SignupPhoneVerificationScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignupPhoneVerificationUIState(),
            onPhoneNumberChange = {},
            onCountryCodeClick = {},
            onNextClicked = {},
            onBackClick = {}
        )
    }
}
