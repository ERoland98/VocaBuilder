package hu.bme.aut.android.vocabuilder.database.dictionary_entries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
The ViewModel's role is to provide data to the UI and acts as a communication center between the Repository and the UI.
 */
class EntryViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Entry>>
    private val repository: EntryRepository

    init {
        val entryDao = EntryDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        readAllData = repository.readAllData
    }

    fun addEntry(entry: Entry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEntry(entry)
        }
    }

    fun updateEntry(entry: Entry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEntry(entry)
        }
    }

    fun deleteEntry(entry: Entry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEntry(entry)
        }
    }

    fun deleteAllEntry() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEntry()
        }
    }
}