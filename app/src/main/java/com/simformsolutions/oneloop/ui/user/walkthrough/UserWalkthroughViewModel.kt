package com.simformsolutions.oneloop.ui.user.walkthrough

import androidx.lifecycle.ViewModel
import com.simform.navigation.Navigator
import com.simformsolutions.oneloop.ui.user.navigation.UserWalkthrough
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The ViewModel for [UserWalkthrough]
 *
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class UserWalkthroughViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    /**
     * Called when users presses back.
     */
    fun onBackClick() {
        navigator.navigateUp()
    }

    fun onExploreAppClick() {
        //navigator.navigate(AboutYou)
    }
}
