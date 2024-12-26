package com.simformsolutions.oneloop.ui.user.signup.signupphonevarification

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import com.simform.design.appbar.AppTopAppBar
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
    val isDialogVisible by viewModel.isDialogVisible

    SignupPhoneVerificationScreen(
        modifier = modifier,
        uiState = uiState,
        onPhoneNumberChange = viewModel::onPhoneNumberChange,
        selectCountryCode = viewModel::selectCountryCode,
        updateCountryCode = viewModel::updateCountryCode,
        onPhoneNumberVerificationNextClicked = viewModel::onPhoneNumberVerificationPageNextClicked,
        onBackClick = viewModel::onBackClick,
        isDialogVisible = isDialogVisible
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show PhoneNumberVerificationPage UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [SignupPhoneVerificationUIState]
 */
@Composable
private fun SignupPhoneVerificationScreen(
    modifier: Modifier,
    uiState: SignupPhoneVerificationUIState,
    onPhoneNumberChange: (String) -> Unit,
    selectCountryCode: () -> Unit,
    updateCountryCode: (String) -> Unit,
    onPhoneNumberVerificationNextClicked: () -> Unit,
    onBackClick: () -> Unit, // New parameter for back click handling
    isDialogVisible: Boolean
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                containerColor = AppTheme.appColorScheme.blackColor,
                centerContent = {
                    AppText(
                        text = stringResource(R.string.verification),
                        textColor = AppTheme.appColorScheme.white
                    )
                },
                leadingContent = {
                    Row(
                        modifier = Modifier
                            .clickable { onBackClick() }
                            .padding(
                                start = dimensionResource(R.dimen.common_space_small),
                                end = dimensionResource(R.dimen.common_space_very_small)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppIcon(
                            modifier = Modifier
                                .padding(end = dimensionResource(R.dimen.common_space_very_small)),
                            painter = painterResource(id = R.drawable.icon_back_arrow)
                        )

                        AppText(
                            text = stringResource(R.string.back),
                            textColor = AppTheme.appColorScheme.white
                        )
                    }
                }
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
                .imePadding()
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
                    .padding(
                        bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)
                    ),
                painter = painterResource(
                    id = R.drawable.icon_smartphone
                )
            )

            AppUnderlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.phoneNumber,
                textStyle = AppTheme.appTypography.body1Normal.copy(color = AppTheme.appColorScheme.white),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.phone_number),
                        textColor = AppTheme.appColorScheme.textColor,
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppIcon(
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.common_space_very_small)),
                            painter = painterResource(
                                id = R.drawable.icon_phone
                            )
                        )

                        Row(
                            modifier = Modifier
                                .clickable {
                                    selectCountryCode()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AppText(
                                text = uiState.countryCode.ifEmpty { stringResource(R.string.select_country_code) }, // Display selected country code
                                textColor = AppTheme.appColorScheme.textColor,
                            )
                            AppIcon(
                                painter = painterResource(id = R.drawable.icon_dropbox), // Your dropdown icon
                                tint = AppTheme.appColorScheme.textColor
                            )

                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.common_space_very_small)))
                        }
                    }
                }
            )

            // Dialog to select country code
            if (isDialogVisible) {
                CountryCodeDialog(countryCodeList = uiState.countryCodeList,
                    onCountryCodeSelect = { selectedCode ->
                        updateCountryCode(selectedCode)
                    },
                    onDismiss = {
                        updateCountryCode(uiState.countryCode)
                    })
            }

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)
                    ),
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.common_space_medium)),
                enabled = uiState.isValidInputOfPhoneNumberVerificationPage,
                onClick = onPhoneNumberVerificationNextClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_next),
                    textColor = AppTheme.appColorScheme.white,
                )
            }
        }
    }
}

@Composable
fun CountryCodeDialog(
    countryCodeList: List<String>,
    onCountryCodeSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.select_country_code)
            )
        },
        text = {
            Column {
                countryCodeList.forEach { countryCode ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_space_medium))
                            .clickable {
                                onCountryCodeSelect(countryCode)
                            },
                        text = countryCode
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss // Optionally handle confirmation
            ) {
                Text(text = stringResource(R.string.close_dialog_close_btn_txt))
            }
        }
    )
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
            updateCountryCode = {},
            selectCountryCode = {},
            onPhoneNumberVerificationNextClicked = {},
            onBackClick = {},
            isDialogVisible = false
        )
    }
}
