package com.simformsolutions.oneloop.ui.user.signup.optverificationpage

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.appbar.CommonTopAppBar
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.scaffold.AppScaffold
import com.simform.design.text.AppText
import com.simform.design.textfield.OtpTextField
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
        onOtpCodeChange = viewModel::onOtpCodeChange,
        onOtpVerifyButtonClick = viewModel::onOtpVerifyButtonClick,
        onReSendClick = viewModel::onReSendClick,
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
    onOtpCodeChange: (String) -> Unit,
    onOtpVerifyButtonClick: () -> Unit,
    onReSendClick: () -> Unit,
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
                OtpTextField(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(),
                    otpText = uiState.otpValues,
                    onOtpTextChange = { value, otpInputFilled ->
                        onOtpCodeChange(value)
                    }
                )
            }

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)
                ),
                onClick = onOtpVerifyButtonClick
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
                            onReSendClick()
                        },
                    text = stringResource(R.string.resend),
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
        SignupOtpVerificationScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignupOtpVerificationUIState(),
            onOtpCodeChange = { _: String -> },
            onOtpVerifyButtonClick = {},
            onReSendClick = {},
            onBackClick = {}
        )
    }
}
