package io.wax911.challenge.data.core.extensions

import io.wax911.challenge.data.android.database.contract.IStore
import io.wax911.challenge.data.api.common.EndpointType
import io.wax911.challenge.data.api.provider.RetrofitProvider
import org.koin.core.scope.Scope


/**
 * Facade for supplying retrofit interface types, backed by [RetrofitProvider]
 *
 * @param endpointType Type of endpoint we wish to use
 */
internal inline fun <reified T> Scope.api(
    endpointType: EndpointType = EndpointType.DEFAULT
): T = RetrofitProvider.provideRetrofit(
    endpointType,
    this
).create(T::class.java)

/**
 * Facade for obtaining a database contract
 */
internal fun Scope.store() = get<IStore>()