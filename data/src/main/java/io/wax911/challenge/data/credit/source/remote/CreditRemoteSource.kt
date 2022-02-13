package io.wax911.challenge.data.credit.source.remote

import io.wax911.challenge.data.api.common.EndpointType
import io.wax911.challenge.data.credit.model.CreditModel
import retrofit2.Response
import retrofit2.http.GET

internal interface CreditRemoteSource {

    @GET(EndpointType.DEFAULT_PATH)
    suspend fun getCreditScore(): Response<CreditModel>
}