package com.simformsolutions.oneloop.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.simformsolutions.oneloop.domain.model.User

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User>
        get() = sequenceOf(
            User(
                login = User.Login(email = "", password = "")
            )
    )
}
