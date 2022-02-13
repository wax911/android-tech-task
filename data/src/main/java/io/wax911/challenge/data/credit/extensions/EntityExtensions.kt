package io.wax911.challenge.data.credit.extensions

import io.wax911.challenge.data.credit.entity.CreditEntity

internal fun CreditEntity.isValid(): Boolean {
    // Given more time we could expand this into a utility to invalidate response caches
    val currentTime = System.currentTimeMillis()
    return expiresAt > currentTime
}