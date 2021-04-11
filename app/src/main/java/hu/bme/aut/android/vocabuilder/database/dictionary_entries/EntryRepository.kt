package hu.bme.aut.android.vocabuilder.database.dictionary_entries

import androidx.lifecycle.LiveData

/*
The class abstracts to multiple data sources.
 */
class EntryRepository(private val entryDao: EntryDao) {
    val readAllData: LiveData<List<Entry>> = entryDao.getAll()

    fun addEntry(entry: Entry) {
        entryDao.insert(entry)
    }

    fun updateEntry(entry: Entry) {
        entryDao.update(entry)
    }

    fun deleteEntry(entry: Entry) {
        entryDao.delete(entry)
    }

    fun deleteAllEntry() {
        entryDao.deleteAll()
    }
}