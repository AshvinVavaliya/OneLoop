package com.simformsolutions.oneloop.ui.user.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.text.AppText
import com.simform.design.textfield.AppUnderlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * Login route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [LoginViewModel] instance
 */
@Composable
fun LoginRoute(
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onForgotPasswordClick = viewModel::onForgotPasswordClick,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        signInClicked = viewModel::signInClicked
    )
}

/**
 * Standalone screen to show login UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [LoginUiState]
 */
@Composable
private fun LoginScreen(
    modifier: Modifier,
    uiState: LoginUiState,
    onForgotPasswordClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signInClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.img_login_bg),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(rememberScrollState())
            .padding(start = dimensionResource(id = R.dimen.main_contains_side_space), end = dimensionResource(id = R.dimen.main_contains_side_space)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppIcon(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)),
            painter = painterResource(id = R.drawable.app_logo)
        )

        // Email TextField
        AppUnderlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            textStyle = AppTheme.appTypography.body1Normal.copy(color = AppTheme.appColorScheme.white),
            placeholder = {
                AppText(
                    text = stringResource(R.string.email_field_hint),
                )
            },
            supportingText = {
                if (uiState.isValidEmailError > 0) {
                    AppText(
                        text = stringResource(uiState.isValidEmailError),
                        textColor = AppTheme.appColorScheme.error
                    )
                }
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

        // Password TextField
        AppUnderlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.password,
            textStyle = AppTheme.appTypography.body1Normal.copy(color = AppTheme.appColorScheme.white),
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
                    painter = painterResource(id = R.drawable.icon_password_field)
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )

        AppText(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .clickable {
                    onForgotPasswordClick()
                },
            text = stringResource(R.string.forgot_password),
            textColor = AppTheme.appColorScheme.textColor
        )

        // Sign In button
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)),
            enabled = uiState.isValidInput,
            onClick = signInClicked
        ) {
            AppText(
                text = stringResource(R.string.btn_sign_in),
                textColor = AppTheme.appColorScheme.white,
            )
        }

        AppText(
            modifier = Modifier
                .padding(30.dp),
            text = stringResource(R.string.connect_with),
            textColor = AppTheme.appColorScheme.textColor
        )

        // Social media buttons
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(id = R.dimen.social_media_buttons_space),
                alignment = Alignment.CenterHorizontally
            )
        ) {
            AppIcon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.social_media_buttons_size))
                    .clickable {
                        Toast.makeText(context, "Facebook Icon Clicked", Toast.LENGTH_SHORT).show()
                    },
                painter = painterResource(id = R.drawable.icon_fb)
            )

            AppIcon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.social_media_buttons_size))
                    .clickable {
                        Toast.makeText(context, "Twitter Icon Clicked", Toast.LENGTH_SHORT).show()
                    },
                painter = painterResource(id = R.drawable.icon_twitter)
            )

            AppIcon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.social_media_buttons_size))
                    .clickable {
                        Toast.makeText(context, "Apple Icon Clicked", Toast.LENGTH_SHORT).show()
                    },
                painter = painterResource(id = R.drawable.icon_apple)
            )
        }

        // Sign Up prompt
        Row {
            AppText(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.common_space_15), bottom = dimensionResource(R.dimen.common_space_10)),
                text = stringResource(R.string.don_t_have_an_account),
                textColor = AppTheme.appColorScheme.textColor,
            )

            AppText(
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.common_space_5), top = dimensionResource(R.dimen.common_space_15), bottom = dimensionResource(R.dimen.common_space_10))
                    .clickable {
                        Toast.makeText(context, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
                    },
                text = stringResource(R.string.sign_up),
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
        LoginScreen(modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(),
            onForgotPasswordClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            signInClicked = {})
    }
}
