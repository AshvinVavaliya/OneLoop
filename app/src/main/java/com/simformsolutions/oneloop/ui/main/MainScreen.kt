package com.simformsolutions.oneloop.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.simform.design.theme.AppTheme
import com.simformsolutions.oneloop.ui.user.navigation.userNavigation
import com.simform.design.surface.AppSurface
import com.simform.navigation.NavigatorLayout
import com.simform.navigation.rememberNavigatorController
import com.simformsolutions.oneloop.ui.user.navigation.LoginRoute

/**
 * The login screen. The root composable of our application.
 */
@Composable
fun MainScreen() {
    AppTheme {
        val mainViewModel: MainViewModel = hiltViewModel()
        val navigatorController = rememberNavigatorController(mainViewModel.navigator)

        AppSurface {
            NavigatorLayout(
                startDestination = LoginRoute::class,
                navigatorController = navigatorController,
                sheetBackgroundColor = AppTheme.appColorScheme.bgColor,
                scrimColor = AppTheme.appColorScheme.bgColor.copy(alpha = 0.85F)
            ) {
                userNavigation()
            }
        }
    }
}
