package hu.bme.aut.android.vocabuilder.database.dictionary_entries

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
Represents a table within the database.
 */
@Parcelize
@Entity(tableName = "dictionary")
data class Entry (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "language_1") val language_1: String,
    @ColumnInfo(name = "word_1") val word_1: String,
    @ColumnInfo(name = "language_2") val language_2: String,
    @ColumnInfo(name = "word_2") val word_2: String
): Parcelable