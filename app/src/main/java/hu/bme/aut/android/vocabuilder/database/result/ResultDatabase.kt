package hu.bme.aut.android.vocabuilder.database.result

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Contains the database holder and serves as the main access point fo the underlying connection to the app's persisted, relational data.
 */
@Database(entities = [Result::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    // Create Singleton class
    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getDatabase(context: Context): ResultDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "result"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}