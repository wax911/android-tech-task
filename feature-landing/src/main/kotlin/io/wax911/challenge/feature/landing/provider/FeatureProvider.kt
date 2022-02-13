package io.wax911.challenge.feature.landing.provider

import androidx.fragment.app.Fragment
import io.wax911.challenge.feature.landing.component.content.LandingContent
import io.wax911.challenge.navigation.LandingRouter

class FeatureProvider : LandingRouter.Provider {
    override fun fragment(): Class<out Fragment> =
        LandingContent::class.java
}