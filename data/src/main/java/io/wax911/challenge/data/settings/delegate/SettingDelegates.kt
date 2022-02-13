package io.wax911.challenge.data.settings.delegate

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.annotation.StringRes
import io.wax911.challenge.data.android.preference.delegate.StringPreference
import io.wax911.challenge.data.settings.contract.AbstractSetting

/**
 * Additional abstraction over [StringPreference] to add support for state changes monitoring
 *
 * @param key A [StringRes] used to lookup the key
 * @param default Default value when the underlying preference with [key] does not exist
 * @param resources Resource used to resolve [key]
 * @param preference The shared preference containing preferences
 *
 * @see AbstractSetting
 */
class StringSetting(
    @StringRes key: Int,
    default: String?,
    resources: Resources,
    preference: SharedPreferences
) : AbstractSetting<String?>(preference, default) {

    override val identifier = resources.getString(key)

    override var value by StringPreference(identifier, default)
}