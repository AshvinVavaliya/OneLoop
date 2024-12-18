package com.simformsolutions.oneloop.ui.user.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.simformsolutions.oneloop.ui.user.login.LoginRoute
import com.simform.navigation.core.composable
import com.simform.navigation.core.navigation
import com.simformsolutions.oneloop.ui.user.forgotpassword.ForgotPasswordRoute

/**
 * The user nav graph.
 */
fun NavGraphBuilder.userNavigation() {
    navigation(
            route = LoginRoute::class,
            startDestination = Login::class
    ) {
        composable<Login> {
            LoginRoute(modifier = Modifier
                .fillMaxSize()
                .imePadding()
            )
        }

        composable<ForgotPassword> {
            ForgotPasswordRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }
    }
}