package com.simformsolutions.oneloop.ui.user.forgotpassword

import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.appbar.AppTopAppBar
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.image.AppImage
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
 * @param modifier The [Modifier]
 * @param viewModel The [ForgotPasswordViewModel] instance
 */
@Composable
fun ForgotPasswordRoute(
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ForgotPasswordScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onBackClick = viewModel::onBackClick,
        validateEmail = viewModel::validateEmail
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show ForgotPassword UI.
 *
 * @param viewModel The [ForgotPasswordViewModel] instance
 * @param uiState The [LoginUiState]
 * @param emailError The [String] parameter
 */
@Composable
private fun ForgotPasswordScreen(
    uiState: ForgotPasswordUIState,
    onBackClick: () -> Unit, // New parameter for back click handling
    onEmailChange: (String) -> Unit,
    validateEmail: (String) -> Unit
) {
    val context = LocalContext.current

    AppScaffold(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Red,
        topBar = {
            AppTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Black,
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        // Back button on the left side
                        Row(modifier = Modifier
                            .fillMaxHeight()
                            .clickable {
                                onBackClick() // Handle back click action here
                            }
                            .padding(
                                start = dimensionResource(
                                    id = R.dimen.dp_10
                                ), end = dimensionResource(
                                    id = R.dimen.dp_5
                                )
                            ),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically) {
                            AppIcon(
                                modifier = Modifier.padding(
                                    end = dimensionResource(
                                        id = R.dimen.dp_5
                                    )
                                ),
                                painter = painterResource(
                                    id = R.drawable.icon_back_arrow
                                ),
                                contentDescription = "Back Icon"
                            )

                            AppText(
                                text = "Back",
                                textColor = AppTheme.appColorScheme.white
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(), // Ensure Box takes full height of the top bar
                            contentAlignment = Alignment.Center // Center content horizontally and vertically
                        ) {
                            AppText(
                                text = "Forgot Password", textColor = Color.White
                            )
                        }
                    }
                })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
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
                        start = dimensionResource(
                            id = R.dimen.dp_25
                        ), end = dimensionResource(
                            id = R.dimen.dp_25
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                        .imePadding(), // Adjust layout for keyboard
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center // Vertically center the content
                ) {
                    AppImage(
                        modifier = Modifier
                            .padding(
                                bottom = dimensionResource(
                                    id = R.dimen.dp_20
                                )
                            ),
                        placeholder = painterResource(
                            id = R.drawable.icon_forgot_password
                        ),
                        url = ""
                    )

                    AppText(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(
                                start = dimensionResource(id = R.dimen.dp_10),
                                end = dimensionResource(id = R.dimen.dp_10),
                                bottom = dimensionResource(id = R.dimen.dp_10)
                            ),
                        text = stringResource(R.string.forgot_password),
                        textColor = Color.White,
                        style = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp // Set the desired font size here
                        )
                    )

                    AppText(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(
                                start = dimensionResource(
                                    id = R.dimen.dp_10
                                ), end = dimensionResource(
                                    id = R.dimen.dp_10
                                ), bottom = dimensionResource(
                                    id = R.dimen.dp_10
                                )
                            ),
                        text = stringResource(R.string.note_of_forgot_password),
                        textColor = AppTheme.appColorScheme.textColor,
                        style = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center
                        )
                    )

                    // Email TextField
                    AppUnderlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.dp_10),
                                top = dimensionResource(id = R.dimen.dp_20),
                                end = dimensionResource(id = R.dimen.dp_10)
                            ), // Push the TextField content to the right to avoid icon overlap
                        value = uiState.email,
                        textStyle = AppTheme.appTypography.body1Normal.copy(
                            color = AppTheme.appColorScheme.white
                        ),
                        supportingText = {
                            val message = if (uiState.isValidEmailError > 0) {
                                stringResource(uiState.isValidEmailError)
                            } else {
                                ""
                            }
                            AppText(text = message, textColor = AppTheme.appColorScheme.error)
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
                            AppImage(
                                modifier = Modifier
                                    .padding(dimensionResource(
                                        id = R.dimen.dp_5
                                    )
                                    ),
                                placeholder = painterResource(
                                    id = R.drawable.icon_email_field
                                ),
                                url = ""
                            )
                        }
                    )

                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(
                                    id = R.dimen.dp_10
                                ), end = dimensionResource(
                                    id = R.dimen.dp_10
                                ), top = dimensionResource(
                                    id = R.dimen.dp_30
                                )
                            ),
                        enabled = uiState.isValidInput,
                        contentPadding = PaddingValues(
                            vertical = dimensionResource(
                                id = R.dimen.dp_15
                            ),
                        ), onClick = {
                            validateEmail(uiState.email)
                        }
                    ) {
                        AppText(
                            text = stringResource(R.string.btn_reset_password),
                            textColor = AppTheme.appColorScheme.white,
                        )
                    }

                    // Sign Up prompt
                    Row(
                        modifier = Modifier.padding(
                            top = dimensionResource(
                                id = R.dimen.dp_10
                            )
                        )
                    ) {
                        AppText(
                            modifier = Modifier.padding(
                                top = dimensionResource(
                                    id = R.dimen.dp_10
                                ), bottom = dimensionResource(
                                    id = R.dimen.dp_10
                                )
                            ),
                            text = stringResource(R.string.don_t_have_an_account),
                            textColor = AppTheme.appColorScheme.textColor,
                        )

                        AppText(
                            modifier = Modifier
                                .padding(
                                    dimensionResource(
                                        id = R.dimen.dp_10
                                    )
                                )
                                .clickable {
                                    Toast
                                        .makeText(context, "Sign Up Clicked", Toast.LENGTH_SHORT)
                                        .show()
                                },
                            text = stringResource(R.string.sign_up),
                            textColor = AppTheme.appColorScheme.white,
                        )
                    }
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
        ForgotPasswordScreen(
            uiState = ForgotPasswordUIState(email = ""),
            onEmailChange = {},
            onBackClick = {},
            validateEmail = {}
        )
    }
}
