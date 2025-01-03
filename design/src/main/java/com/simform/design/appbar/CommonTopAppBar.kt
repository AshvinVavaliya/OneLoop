package com.simform.design.appbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.simform.design.R
import com.simform.design.icon.AppIcon
import com.simform.design.text.AppText
import com.simform.design.theme.AppTheme

@Composable
fun CommonTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = true, // Add option to show/hide the back button
    onBackClick: () -> Unit
) {
    AppTopAppBar(
        modifier = modifier,
        containerColor = AppTheme.appColorScheme.blackColor,
        leadingContent = {
            if (showBackButton) {
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
        },
        centerContent = {
            AppText(
                text = title,
                textColor = AppTheme.appColorScheme.white
            )
        }
    )
}