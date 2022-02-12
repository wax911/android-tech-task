package io.wax911.challenge.core.settings

import android.content.Context
import io.wax911.challenge.core.R
import io.wax911.challenge.data.android.preference.AbstractPreference
import io.wax911.challenge.data.settings.IUserSettings
import io.wax911.challenge.data.settings.delegate.StringSetting

/**
 * Facade around shared preference, with support for observing properties for changes
 */
class Settings(context: Context) : AbstractPreference(context), IUserSettings {

    override val userId = StringSetting(
        key = R.string.setting_user_id,
        default = null,
        resources = context.resources,
        preference = this
    )
}