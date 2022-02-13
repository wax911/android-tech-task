package io.wax911.challenge.feature.detail.provider

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import io.wax911.challenge.feature.detail.component.content.DetailContent
import io.wax911.challenge.feature.detail.component.screen.DetailScreen
import io.wax911.challenge.navigation.DetailRouter

class FeatureProvider : DetailRouter.Provider {
    override fun activity(context: Context): Intent =
        Intent(context, DetailScreen::class.java)

    override fun fragment(): Class<out Fragment> =
        DetailContent::class.java
}