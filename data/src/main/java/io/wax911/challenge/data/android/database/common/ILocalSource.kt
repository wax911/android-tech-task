package io.wax911.challenge.data.android.database.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
internal interface ILocalSource<T> {

    /**
     * Inserts a new item into the database replacing an item with the same primary key
     *
     * @param attribute item/s to insert/update
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(attribute: T): Long


    /**
     * Inserts a new item into the database replacing items with the same primary key
     *
     * @param attribute item/s to insert/update
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(attribute: List<T>): List<Long>

    /**
     * Inserts a new item into the database ignoring items with the same primary key,
     * for both insert or update behavior use: [upsert]
     *
     * @param attribute item/s to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attribute: T): Long

    /**
     * Inserts new items into the database ignoring items with the same primary key,
     * for both insert or update behavior use: [upsert]
     *
     * @param attribute item/s to insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attribute: List<T>): List<Long>
}