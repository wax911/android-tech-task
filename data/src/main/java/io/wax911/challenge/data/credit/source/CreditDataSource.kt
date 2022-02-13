package io.wax911.challenge.data.credit.source

import io.wax911.challenge.data.core.error.RequestError
import io.wax911.challenge.data.core.extensions.fetch
import io.wax911.challenge.data.credit.converter.CreditConverter
import io.wax911.challenge.data.credit.entity.CreditEntity
import io.wax911.challenge.data.credit.extensions.isValid
import io.wax911.challenge.data.credit.source.local.CreditLocalSource
import io.wax911.challenge.data.credit.source.remote.CreditRemoteSource
import io.wax911.challenge.data.settings.IClientSettings
import retrofit2.HttpException
import java.net.SocketTimeoutException
import kotlin.jvm.Throws

/**
 * Login repository implementation
 *
 * @param localSource Source for accessing local backed storage
 * @param remoteSource Source for accessing remote resources
 * @param settings Settings facade for authentication settings
 * @param converter Mapper for converting models to entities
 */
internal class CreditDataSource(
    private val localSource: CreditLocalSource,
    private val remoteSource: CreditRemoteSource,
    private val settings: IClientSettings
) {
    private suspend fun getCachedEntity(): CreditEntity? {
        val clientId = settings.clientId.value

        return clientId?.let {
            localSource.getCreditForClient(it)
        }
    }

    @Throws(HttpException::class, RequestError::class, SocketTimeoutException::class)
    suspend operator fun invoke(): CreditEntity {
        val cachedEntity = getCachedEntity()
        if (cachedEntity?.isValid() == true)
            return cachedEntity

        val model = remoteSource.getCreditScore().fetch()

        settings.clientId.value = model.creditReportInfo.clientRef

        val entity = CreditConverter.from(model)

        localSource.upsert(entity)

        return entity
    }
}