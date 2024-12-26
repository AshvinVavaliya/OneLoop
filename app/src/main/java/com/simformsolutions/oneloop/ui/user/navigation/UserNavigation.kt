package com.simformsolutions.oneloop.ui.user.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.simformsolutions.oneloop.ui.user.login.LoginRoute
import com.simform.navigation.core.composable
import com.simform.navigation.core.navigation
import com.simformsolutions.oneloop.ui.user.forgotpassword.ForgotPasswordRoute
import com.simformsolutions.oneloop.ui.user.signup.optverificationpage.SignupOtpVerificationRoute
import com.simformsolutions.oneloop.ui.user.signup.signuppassword.SignupPasswordRoute
import com.simformsolutions.oneloop.ui.user.signup.signupphonevarification.SignupPhoneVerificationRoute
import com.simformsolutions.oneloop.ui.user.signup.signupuserinfo.SignupUserInfoRoute

/**
 * The user nav graph.
 */
fun NavGraphBuilder.userNavigation() {
    navigation(
        route = LoginRoute::class,
        startDestination = Login::class
    ) {
        composable<Login> {
            LoginRoute(
                modifier = Modifier
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

        composable<SignupUserInfo> {
            SignupUserInfoRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }

        composable<SignupPassword> {
            SignupPasswordRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }

        composable<SignupPhoneVerification> {
            SignupPhoneVerificationRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }

        composable<SignupOtpVerification> {
            SignupOtpVerificationRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }
    }
}