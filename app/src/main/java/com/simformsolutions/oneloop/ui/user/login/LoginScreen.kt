package com.simformsolutions.oneloop.ui.user.login

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.button.AppButton
import com.simform.design.image.AppImage
import com.simform.design.text.AppText
import com.simform.design.textfield.AppUnderlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * Login route to show UI.
 *
 * @param viewModel The [LoginViewModel] instance
 */
@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreen(
        uiState = uiState,
        onForgotPasswordClick = viewModel::onForgotPasswordClick,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        validateInput = viewModel::validateInput
    )
}

/**
 * Standalone screen to show login UI.
 *
 * @param uiState The [LoginUiState]
 */
@Composable
private fun LoginScreen(
    uiState: LoginUiState,
    onForgotPasswordClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    validateInput: (String) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AppImage(
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(R.drawable.img_login_bg),
            url = "",
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxSize() // Ensure Box takes up the full screen
                .background(Color.Transparent)
                .padding(
                    start = dimensionResource(id = R.dimen.dp_25),
                    end = dimensionResource(id = R.dimen.dp_25)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                    .imePadding(), // Adjust layout for keyboard (when it's open)
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // Vertically center the content
            ) {
                // App Logo at the top
                AppImage(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dp_40)),
                    placeholder = painterResource(id = R.drawable.app_logo), // Replace with your logo resource
                    url = ""
                )

                // Email TextField
                AppUnderlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.dp_10),
                            end = dimensionResource(id = R.dimen.dp_10)
                        ), // Push the TextField content to the right to avoid icon overlap
                    value = uiState.email,
                    textStyle = AppTheme.appTypography.body1Normal.copy(color = AppTheme.appColorScheme.white),
                    placeholder = {
                        AppText(
                            text = stringResource(R.string.email_field_hint),
                            textColor = AppTheme.appColorScheme.textColor,
                        )
                    },
                    supportingText = {
                        if (uiState.isValidEmailError > 0) {
                            AppText(text = stringResource(uiState.isValidEmailError), textColor = AppTheme.appColorScheme.error)
                        }
                    },
                    onValueChange = onEmailChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    leadingIcon = {
                        AppImage(
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.dp_5)),
                            placeholder = painterResource(id = R.drawable.icon_email_field),
                            url = ""
                        )
                    }
                )

                // Password TextField
                AppUnderlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(
                                id = R.dimen.dp_10
                            ),
                            top = dimensionResource(
                                id = R.dimen.dp_20
                            ),
                            end = dimensionResource(
                                id = R.dimen.dp_10
                            )
                        ),
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
                            AppText(text = stringResource(uiState.isValidPasswordError), textColor = AppTheme.appColorScheme.error)
                        }
                    },
                    onValueChange = onPasswordChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        capitalization = KeyboardCapitalization.Words
                    ),
                    leadingIcon = {
                        AppImage(
                            modifier = Modifier.padding(
                                dimensionResource(id = R.dimen.dp_5)
                            ),
                            placeholder = painterResource(
                                id = R.drawable.icon_password_field),
                            url = "",
                        )
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                // Forgot password text
                AppText(
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                        .padding(dimensionResource(id = R.dimen.dp_10))
                        .clickable {
                            onForgotPasswordClick()
                        },
                    text = stringResource(R.string.forgot_password),
                    textColor = AppTheme.appColorScheme.textColor,
                    style = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Right
                    )
                )

                // Sign In button
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.dp_20)),
                    contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.dp_15)),
                    enabled = uiState.isValidInput,
                    onClick = { validateInput(uiState.email) }
                ) {
                    AppText(
                        text = stringResource(R.string.btn_sign_in),
                        textColor = AppTheme.appColorScheme.white,
                    )
                }

                // "Connect with" text
                AppText(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(
                            start = dimensionResource(id = R.dimen.dp_10),
                            end = dimensionResource(id = R.dimen.dp_10)
                        ),
                    text = stringResource(R.string.connect_with),
                    textColor = AppTheme.appColorScheme.textColor
                )

                // Social media buttons
                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dp_20))
                ) {
                    AppImage(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_70))
                            .padding(dimensionResource(id = R.dimen.dp_10))
                            .clickable {
                                // Your onClick action here
                                // For example, show a Toast or navigate to a different screen
                                Toast
                                    .makeText(
                                        context, "Facebook Icon Clicked", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                        contentScale = ContentScale.FillBounds,
                        url = "",
                        placeholder = painterResource(R.drawable.icon_fb)
                    )

                    AppImage(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_70))
                            .padding(dimensionResource(id = R.dimen.dp_10))
                            .clickable {
                                // Your onClick action here
                                // For example, show a Toast or navigate to a different screen
                                Toast
                                    .makeText(
                                        context, "Twitter Icon Clicked", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                        contentScale = ContentScale.FillBounds,
                        url = "",
                        placeholder = painterResource(R.drawable.icon_twitter)
                    )

                    AppImage(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp_70))
                            .padding(dimensionResource(id = R.dimen.dp_10))
                            .clickable {
                                // Your onClick action here
                                // For example, show a Toast or navigate to a different screen
                                Toast
                                    .makeText(
                                        context, "Apple Icon Clicked", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                        contentScale = ContentScale.FillBounds,
                        url = "",
                        placeholder = painterResource(R.drawable.icon_apple)
                    )
                }

                // Sign Up prompt
                Row {
                    AppText(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.dp_10),
                            bottom = dimensionResource(id = R.dimen.dp_10)
                        ),
                        text = stringResource(R.string.don_t_have_an_account),
                        textColor = AppTheme.appColorScheme.textColor,
                    )

                    AppText(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dp_10))
                            .clickable {
                                Toast.makeText(
                                    context, "Sign Up Clicked", Toast.LENGTH_SHORT
                                ).show()
                            },
                        text = stringResource(R.string.sign_up),
                        textColor = AppTheme.appColorScheme.white,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        LoginScreen(
            uiState = LoginUiState(email = "", password = ""),
            onForgotPasswordClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            validateInput = {}
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingLoginScreenPreview() {
    AppPreviewTheme {
        LoginScreen(
            uiState = LoginUiState(email = "", password = ""),
            onForgotPasswordClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            validateInput = {}
        )
    }
}
