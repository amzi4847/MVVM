package com.suraj.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class subscriber_database : RoomDatabase() {

    abstract val subscriber_dao: subscriber_dao

    companion object {

        @Volatile
        private var INSTANCE: subscriber_database? = null

        fun getInstance(context: Context): subscriber_database {

            synchronized(this) {

                var instance: subscriber_database? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        subscriber_database::class.java,
                        "subscriber_table"
                    ).build()
                }

                return instance
            }
        }


    }
}