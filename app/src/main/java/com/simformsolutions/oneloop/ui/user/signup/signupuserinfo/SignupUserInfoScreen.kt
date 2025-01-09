package com.simformsolutions.oneloop.ui.user.signup.signupuserinfo

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
 * SignupUserInfo route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [SignupUserInfoViewModel] instance
 */
@Composable
fun SignupUserInfoRoute(
    modifier: Modifier,
    viewModel: SignupUserInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SignupUserInfoScreen(
        modifier = modifier,
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onBackClick = viewModel::onBackClick,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onUserInfoNextClicked = viewModel::onUserInfoPageNextClicked,
        onPickImageClick = viewModel::onPickImageClick,
        onSignInClick = viewModel::onSignInClick
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show UserInfoPage UI.
 *
 * @param modifier The [Modifier]
 * @param uiState The [SignupUserInfoUIState]
 */
@Composable
private fun SignupUserInfoScreen(
    modifier: Modifier,
    uiState: SignupUserInfoUIState,
    onBackClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onUserInfoNextClicked: () -> Unit,
    onPickImageClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            CommonTopAppBar(
                title = stringResource(R.string.sign_up),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .paint(
                    painter = painterResource(id = R.drawable.img_login_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(innerPadding)
                .padding(
                    start = dimensionResource(id = R.dimen.main_contains_side_space),
                    end = dimensionResource(id = R.dimen.main_contains_side_space)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)
                    )
                    .size(
                        width = dimensionResource(id = R.dimen.avatar_box_width),
                        height = dimensionResource(id = R.dimen.avatar_box_height)
                    ),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.common_space_small))
                        .matchParentSize(),
                    painter = painterResource(id = R.drawable.icon_avatar_border)
                )

                AppIcon(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(top = dimensionResource(id = R.dimen.common_space_medium),),
                    painter = painterResource(id = R.drawable.icon_avatar)
                )

                AppIcon(
                    modifier = Modifier
                        .padding(
                            end = dimensionResource(id = R.dimen.common_space_extra_large),
                            top = dimensionResource(id = R.dimen.common_space_large)
                        )
                        .align(Alignment.BottomEnd)
                        .clickable { onPickImageClick() },
                    painter = painterResource(id = R.drawable.icon_pick_avatar)
                )
            }

            AppUnderlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.firstName,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.first_name),
                        textColor = AppTheme.appColorScheme.textColor,
                    )
                },
                supportingText = {
                    if (uiState.isValidFirstNameError > 0) {
                        AppText(
                            text = stringResource(uiState.isValidFirstNameError),
                            textColor = AppTheme.appColorScheme.error
                        )
                    }
                },
                onValueChange = onFirstNameChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                leadingIcon = {
                    AppIcon(
                        painter = painterResource(id = R.drawable.icon_of_user)
                    )
                }
            )

            AppUnderlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.lastName,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.last_name),
                        textColor = AppTheme.appColorScheme.textColor,
                    )
                },
                supportingText = {
                    if (uiState.isValidLastNameError > 0) {
                        AppText(
                            text = stringResource(uiState.isValidLastNameError),
                            textColor = AppTheme.appColorScheme.error
                        )
                    }
                },
                onValueChange = onLastNameChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                ),
                leadingIcon = {
                    AppIcon(
                        painter = painterResource(id = R.drawable.icon_of_user)
                    )
                }
            )

            AppUnderlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.email,
                textStyle = AppTheme.appTypography.body1Normal.copy(
                    color = AppTheme.appColorScheme.white
                ),
                placeholder = {
                    AppText(
                        text = stringResource(R.string.email_field_hint),
                        textColor = AppTheme.appColorScheme.textColor,
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
                }
            )

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.login_and_forgot_pass_buttons_top_padding)),
                contentPadding = PaddingValues(
                    vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)
                ),
                enabled = uiState.isValidInputOfUserInfoPage,
                onClick = onUserInfoNextClicked
            ) {
                AppText(
                    text = stringResource(R.string.btn_next),
                    textColor = AppTheme.appColorScheme.white,
                )
            }

            Row {
                AppText(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.common_space_medium),
                            bottom = dimensionResource(R.dimen.common_space_small)
                        ),
                    text = stringResource(R.string.have_an_account),
                    textColor = AppTheme.appColorScheme.textColor,
                )

                AppText(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(R.dimen.common_space_very_small),
                            top = dimensionResource(R.dimen.common_space_medium),
                            bottom = dimensionResource(R.dimen.common_space_small)
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
        SignupUserInfoScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = SignupUserInfoUIState(),
            onEmailChange = {},
            onFirstNameChange = {},
            onLastNameChange = {},
            onBackClick = {},
            onUserInfoNextClicked = {},
            onPickImageClick = {},
            onSignInClick = {}
        )
    }
}
