package io.wax911.challenge.data.credit.source.local

import androidx.room.Dao
import androidx.room.Query
import io.wax911.challenge.data.android.database.common.ILocalSource
import io.wax911.challenge.data.credit.entity.CreditEntity

@Dao
internal interface CreditLocalSource : ILocalSource<CreditEntity> {
    @Query("""
            select * from credit
            where id = :clientId
        """)
    suspend fun getCreditForClient(clientId: String): CreditEntity?
}