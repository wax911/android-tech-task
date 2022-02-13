package io.wax911.challenge.data.settings

import io.wax911.challenge.data.settings.contract.AbstractSetting

interface IClientSettings {
    val clientId: AbstractSetting<String?>
}