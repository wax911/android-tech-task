package io.wax911.challenge.data.android.database.contract

import io.wax911.challenge.data.credit.source.local.CreditLocalSource

/**
 * Database DAO container
 */
internal interface IStore {
    fun creditDao(): CreditLocalSource
}