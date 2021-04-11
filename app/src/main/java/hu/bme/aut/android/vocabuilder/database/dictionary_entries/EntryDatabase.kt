package hu.bme.aut.android.vocabuilder.database.dictionary_entries

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Contains the database holder and serves as the main access point fo the underlying connection to the app's persisted, relational data.
 */
@Database(entities = [Entry::class], version = 1, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    // Create Singleton class
    companion object {
        @Volatile
        private var INSTANCE: EntryDatabase? = null

        fun getDatabase(context: Context): EntryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntryDatabase::class.java,
                    "dictionary"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}