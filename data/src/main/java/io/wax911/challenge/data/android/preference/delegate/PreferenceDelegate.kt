package io.wax911.challenge.data.android.preference.delegate

import androidx.core.content.edit
import io.wax911.challenge.data.android.preference.delegate.contract.IPreferenceDelegate
import io.wax911.challenge.data.settings.contract.AbstractSetting
import kotlin.reflect.KProperty

/**
 * [Long] preference delegate for handling preference related
 * operations of getting and saving a value
 */
internal class StringPreference(
    override val key: String,
    override val default: String?
) : IPreferenceDelegate<String?> {

    override fun getValue(
        thisRef: AbstractSetting<String?>,
        property: KProperty<*>
    ): String? {
        return thisRef.preference.getString(key, default)
    }

    override fun setValue(
        thisRef: AbstractSetting<String?>,
        property: KProperty<*>,
        value: String?
    ) {
        thisRef.preference.edit {
            putString(key, value)
        }
    }
}