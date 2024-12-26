package com.simformsolutions.oneloop.ui.main

import androidx.lifecycle.ViewModel
import com.simform.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The ViewModel for [MainScreen]
 *
 * @property navigator The instance of Navigator
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val navigator: Navigator
) : ViewModel()