package hu.bme.aut.android.vocabuilder.database.result

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
Represents a table within the database.
 */
@Parcelize
@Entity(tableName = "result")
data class Result (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "score") val score: String?,
): Parcelable