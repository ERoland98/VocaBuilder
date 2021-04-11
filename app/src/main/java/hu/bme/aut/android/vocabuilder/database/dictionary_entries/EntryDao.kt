package hu.bme.aut.android.vocabuilder.database.dictionary_entries

import androidx.lifecycle.LiveData
import androidx.room.*

/*
Contains the methods used for accessing the database.
 */
@Dao
interface  EntryDao {
    @Query("SELECT * FROM dictionary ORDER BY id ASC")
    fun getAll(): LiveData<List<Entry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entries: Entry): Long

    @Update
    fun update(entries: Entry)

    @Delete
    fun delete(entries: Entry)

    @Query("DELETE FROM dictionary")
    fun deleteAll()
}