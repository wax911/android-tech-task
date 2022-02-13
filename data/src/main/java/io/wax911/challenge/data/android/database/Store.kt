package io.wax911.challenge.data.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.wax911.challenge.data.android.database.contract.IStore
import io.wax911.challenge.data.credit.entity.CreditEntity
import org.jetbrains.annotations.TestOnly

@Database(
    entities = [CreditEntity::class],
    version = Store.SCHEMA_VERSION
)
internal abstract class Store : RoomDatabase(), IStore {

    companion object {
        const val SCHEMA_VERSION = 1

        internal fun create(context: Context): IStore {
            return Room.databaseBuilder(
                context,
                Store::class.java,
                "credit-db"
            ).fallbackToDestructiveMigration()
                .build()
        }

        @TestOnly
        internal fun createInMemory(context: Context): IStore {
            return Room.inMemoryDatabaseBuilder(
                context, Store::class.java,
            ).build()
        }
    }
}