package com.simformsolutions.oneloop.ui.user.forgotpassword

import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
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
import com.simformsolutions.oneloop.ui.user.login.LoginUiState

/**
 * ForgotPassword route to show UI.
 *
 * @param viewModel The [ForgotPasswordViewModel] instance
 */
@Composable
fun ForgotPasswordRoute(
    modifier: Modifier,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ForgotPasswordScreen(
        modifier = modifier,
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onBackClick = viewModel::onBackClick,
        forgotPasswordClicked = viewModel::forgotPasswordClicked
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show ForgotPassword UI.
 *
 * @param uiState The [LoginUiState]
 */
@Composable
private fun ForgotPasswordScreen(
    modifier: Modifier,
    uiState: ForgotPasswordUIState,
    onBackClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    forgotPasswordClicked: () -> Unit
) {
    val context = LocalContext.current

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                containerColor = AppTheme.appColorScheme.blackColor,
                centerContent = {
                    AppText(
                        text = stringResource(R.string.forgot_password_title),
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
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)),
                painter = painterResource(id = R.drawable.icon_forgot_password)
            )

            AppText(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = dimensionResource(R.dimen.common_space_small)),
                text = stringResource(R.string.forgot_password),
                textColor = AppTheme.appColorScheme.white,
                style = AppTheme.appTypography.h6SemiBold.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )

            AppText(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = dimensionResource(R.dimen.common_space_medium)),
                text = stringResource(R.string.note_of_forgot_password),
                textColor = AppTheme.appColorScheme.textColor,
                style = AppTheme.appTypography.body2Normal.copy(textAlign = TextAlign.Center)
            )

            // Email TextField
            AppUnderlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                supportingText = {
                    if (uiState.isValidEmailError > 0) {
                        AppText(text = stringResource(uiState.isValidEmailError), textColor = AppTheme.appColorScheme.error)
                    }
                },
                placeholder = {
                    AppText(
                        text = stringResource(R.string.forgot_pass_for_email_address_field_hint),
                        textColor = AppTheme.appColorScheme.textColor,
                    )
                },
                onValueChange = onEmailChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                leadingIcon = {
                    AppIcon(
                        painter = painterResource(id = R.drawable.icon_email_field)
                    )
                })

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
                enabled = uiState.isValidInput,
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)),
                onClick = forgotPasswordClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_reset_password),
                    textColor = AppTheme.appColorScheme.white,
                )
            }

            // Sign Up prompt
            Row(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.common_space_small)
                )
            ) {
                AppText(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.common_space_small),
                            bottom = dimensionResource(R.dimen.common_space_small)
                        ),
                    text = stringResource(R.string.don_t_have_an_account),
                    textColor = AppTheme.appColorScheme.textColor,
                )

                AppText(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.common_space_small))
                        .clickable {
                            Toast.makeText(context, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
                        },
                    text = stringResource(R.string.sign_up),
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
        ForgotPasswordScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = ForgotPasswordUIState(),
            onEmailChange = {},
            onBackClick = {},
            forgotPasswordClicked = {}
        )
    }
}
