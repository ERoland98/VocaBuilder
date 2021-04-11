package hu.bme.aut.android.vocabuilder.database.result

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface  ResultDao {
    @Query("SELECT * FROM result ORDER BY id ASC")
    fun getAll(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(result: Result): Long

    @Query("DELETE FROM result")
    fun deleteAll()
}