package com.vb.exquisitiveapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vb.exquisitiveapp.model.Challenge

@Database(entities = [Challenge::class], version = 1, exportSchema = false)
abstract class ChallengeDatabase : RoomDatabase() {

    abstract fun challengeDao(): ChallengeDao

    companion object {
        @Volatile
        private var INSTANCE: ChallengeDatabase? = null


        fun getDatabase(context: Context): ChallengeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChallengeDatabase::class.java,
                    "challenge-database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}