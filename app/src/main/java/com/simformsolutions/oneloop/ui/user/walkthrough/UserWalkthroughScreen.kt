package com.simformsolutions.oneloop.ui.user.walkthrough

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.simform.design.button.AppButton
import com.simform.design.icon.AppIcon
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.R

/**
 * UserWalkthrough route to show UI.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [UserWalkthroughViewModel] instance
 */
@Composable
fun UserWalkthroughRoute(
    modifier: Modifier = Modifier,
    viewModel: UserWalkthroughViewModel = hiltViewModel()
) {
    UserWalkthroughScreen(
        modifier = modifier,
        onExploreAppClick = viewModel::onExploreAppClick
    )

    BackHandler {
        viewModel.onBackClick()
    }
}

/**
 * Standalone screen to show UserWalkthrough UI.
 */
@Composable
private fun UserWalkthroughScreen(
    modifier: Modifier = Modifier,
    onExploreAppClick: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(color = AppTheme.appColorScheme.blackColor)
            .imePadding()
            .padding(
                start = dimensionResource(id = R.dimen.main_contains_side_space_of_walkthrough_screen),
                end = dimensionResource(id = R.dimen.main_contains_side_space_of_walkthrough_screen)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppIcon(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.app_icons_bottom_space_for_walkthrough_screen),
                    bottom = dimensionResource(id = R.dimen.app_icons_bottom_space)
                ),
            painter = painterResource(id = R.drawable.app_logo)
        )

        AppText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_space_medium),
                    bottom = dimensionResource(R.dimen.common_space_small)
                ),
            text = stringResource(R.string.who_is_body_boss_contain_title),
            textColor = AppTheme.appColorScheme.white,
            style = AppTheme.appTypography.h4SemiBold.copy(
                textAlign = TextAlign.Left
            )
        )

        AppText(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.contain_of_walkthrough_screen).trimIndent(),
            textColor = AppTheme.appColorScheme.textColor,
            style = AppTheme.appTypography.body1Normal.copy(
                textAlign = TextAlign.Left
            )
        )

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.walkthrough_buttons_padding),
                    end = dimensionResource(id = R.dimen.walkthrough_buttons_padding),
                    bottom = dimensionResource(id = R.dimen.walkthrough_buttons_padding)
                ),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.app_bottom_content_padding)),
            onClick = onExploreAppClick
        ) {
            AppText(
                text = stringResource(R.string.explore_app_btn_text),
                textColor = AppTheme.appColorScheme.white
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() {
    AppPreviewTheme {
        UserWalkthroughScreen(
            modifier = Modifier.fillMaxSize(),
            onExploreAppClick = {}
        )
    }
}
