package com.simformsolutions.oneloop.ui.user.signup.optverificationpage

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.simformsolutions.oneloop.ui.user.signup.signupoptverification.SignupOtpVerificationUIState
import com.simformsolutions.oneloop.ui.user.signup.signupoptverification.SignupOtpVerificationViewModel

/**
 * SignupOtpVerification route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [SignupOtpVerificationViewModel] instance
 */
@Composable
fun SignupOtpVerificationRoute(
    modifier: Modifier,
    viewModel: SignupOtpVerificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SignupOtpVerificationScreen(
        modifier = modifier,
        uiState = uiState,
        onOtpCodeChanged = viewModel::onOtpCodeChanged,
        onOtpVerifyButtonClicked = viewModel::onOtpVerifyButtonClicked,
        onBackClick = viewModel::onBackClick,
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show OtpVerificationPage UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [SignupOtpVerificationUIState]
 */
@Composable
private fun SignupOtpVerificationScreen(
    modifier: Modifier,
    uiState: SignupOtpVerificationUIState,
    onOtpCodeChanged: (Int, String) -> Unit,
    onOtpVerifyButtonClicked: () -> Unit,
    onBackClick: () -> Unit
) {

    val focusRequesters = remember { List(4) { FocusRequester() } }

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
                    .padding(bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)),
                painter = painterResource(id = R.drawable.icon_postal_verification)
            )

            AppText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        top = dimensionResource(R.dimen.common_space_large),
                        bottom = dimensionResource(R.dimen.common_space_large)
                    ),
                text = stringResource(R.string.verify_your_mobile_title),
                textColor = AppTheme.appColorScheme.white,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
            )

            AppText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.verify_your_mobile_contain),
                textColor = AppTheme.appColorScheme.textColor,
                style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Center)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.common_space_small)),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.otp_fields_top_space_of_signup_page))
            ) {
                repeat(4) { index ->
                    AppUnderlinedTextField(
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesters[index]),
                        value = uiState.otpValues.getOrElse(index) { "" },
                        textStyle = AppTheme.appTypography.body1Normal.copy(
                            color = AppTheme.appColorScheme.white,
                            textAlign = TextAlign.Center
                        ),
                        onValueChange = { value ->
                            onOtpCodeChanged(index, value)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        maxLines = 1
                    )
                }
            }

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)
                ),
                onClick = onOtpVerifyButtonClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_verify),
                    textColor = AppTheme.appColorScheme.white,
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.common_space_medium))
            ) {
                AppText(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.common_space_small)),
                    text = stringResource(R.string.did_not_receive_a_code_message),
                    textColor = AppTheme.appColorScheme.textColor,
                )

                AppText(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.common_space_very_small),
                            bottom = dimensionResource(R.dimen.common_space_small)
                        )
                        .clickable {
                            // onReSendClick()
                            // Implement resend OTP functionality
                        },
                    text = stringResource(R.string.resend),
                    textColor = AppTheme.appColorScheme.white,
                )
            }
        }
    }

    // Handle focus logic for OTP fields
    LaunchedEffect(uiState.otpValues) {
        val focusedIndex = uiState.focusedIndex
        if (focusedIndex in 0..3) {
            focusRequesters[focusedIndex].requestFocus()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        SignupOtpVerificationScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignupOtpVerificationUIState(),
            onOtpCodeChanged = { _: Int, _: String -> },
            onOtpVerifyButtonClicked = {},
            onBackClick = {}
        )
    }
}
