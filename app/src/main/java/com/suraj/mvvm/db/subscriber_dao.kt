package com.suraj.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface subscriber_dao {

    @Insert
    suspend fun insert_subscriber(subscriber: Subscriber): Long

    @Insert
    suspend fun insert_subscriber(Subscribers: List<Subscriber>):List<Long>

    @Update
    suspend fun update_subscriber(subscriber: Subscriber)

    @Delete
    suspend fun delete_subscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_table")
    suspend fun delete_all()

    @Query("SELECT * FROM subscriber_table")
    fun getSubscribers():LiveData<List<Subscriber>>
}