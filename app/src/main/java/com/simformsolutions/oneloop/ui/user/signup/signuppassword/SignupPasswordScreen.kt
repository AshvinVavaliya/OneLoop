package com.simformsolutions.oneloop.ui.user.signup.signuppassword

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.appbar.AppTopAppBar
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.scaffold.AppScaffold
import com.simform.design.text.AppText
import com.simform.design.textfield.AppUnderlinedPasswordTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * SignupPassword route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [SignupPasswordViewModel] instance
 */
@Composable
fun SignupPasswordRoute(
    modifier: Modifier,
    viewModel: SignupPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignupPasswordScreen(
        modifier = modifier,
        uiState = uiState,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onPasswordNextClicked = viewModel::onPasswordNextClicked,
        onBackClick = viewModel::onBackClick,
        onSignInClick = viewModel::onSignInClick
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show PasswordPage UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [SignupPasswordUIState]
 */
@Composable
private fun SignupPasswordScreen(
    modifier: Modifier,
    uiState: SignupPasswordUIState,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordNextClicked: () -> Unit,
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                containerColor = AppTheme.appColorScheme.blackColor,
                centerContent = {
                    AppText(
                        text = stringResource(R.string.sign_up),
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
                                .padding(
                                    end = dimensionResource(R.dimen.common_space_very_small)
                                ),
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
                painter = painterResource(id = R.drawable.icon_password_of_signup)
            )

            // Password TextField
            AppUnderlinedPasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.common_space_large)
                    ),
                value = uiState.password,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.password_field_hint),
                        textColor = AppTheme.appColorScheme.textColor
                    )
                },
                supportingText = {
                    if (uiState.isValidPasswordError > 0) {
                        AppText(
                            text = stringResource(uiState.isValidPasswordError),
                            textColor = AppTheme.appColorScheme.error
                        )
                    }
                },
                onValueChange = onPasswordChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                leadingIcon = {
                    AppIcon(
                        modifier = Modifier
                            .padding(
                                end = dimensionResource(id = R.dimen.common_space_very_small)
                            ),
                        painter = painterResource(
                            id = R.drawable.icon_password_field
                        )
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            // Confirm Password TextField
            AppUnderlinedPasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.common_space_large)
                    ),
                value = uiState.confirmPassword,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.confirm_password_field_hint),
                        textColor = AppTheme.appColorScheme.textColor
                    )
                },
                supportingText = {
                    if (uiState.isValidConfirmPasswordError > 0) {
                        AppText(
                            text = stringResource(uiState.isValidConfirmPasswordError),
                            textColor = AppTheme.appColorScheme.error
                        )
                    }
                },
                onValueChange = onConfirmPasswordChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                leadingIcon = {
                    AppIcon(
                        modifier = Modifier
                            .padding(
                                end = dimensionResource(id = R.dimen.common_space_very_small)
                            ),
                        painter = painterResource(
                            id = R.drawable.icon_password_field
                        )
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)
                    ),
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = R.dimen.common_space_medium)
                ),
                enabled = uiState.isValidInputOfPasswordPage,
                onClick = onPasswordNextClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_next),
                    textColor = AppTheme.appColorScheme.white,
                )
            }

            Row {
                AppText(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.common_space_medium),
                        bottom = dimensionResource(id = R.dimen.common_space_medium)
                    ),
                    text = stringResource(R.string.have_an_account),
                    textColor = AppTheme.appColorScheme.textColor,
                )

                AppText(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.common_space_small),
                            top = dimensionResource(id = R.dimen.common_space_medium),
                            bottom = dimensionResource(id = R.dimen.common_space_medium)
                        )
                        .clickable {
                            onSignInClick()
                        },
                    text = stringResource(R.string.sign_in),
                    textColor = AppTheme.appColorScheme.white,
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
        SignupPasswordScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignupPasswordUIState(),
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onPasswordNextClicked = {},
            onBackClick = {},
            onSignInClick = {}
        )
    }
}
