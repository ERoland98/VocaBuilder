package hu.bme.aut.android.vocabuilder.database.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
The ViewModel's role is to provide data to the UI and acts as a communication center between the Repository and the UI.
 */
class ResultViewModel(application: Application) : AndroidViewModel(application) {
    val  readAllData: LiveData<List<Result>>
    private val repository: ResultRepository

    init {
        val resultDao = ResultDatabase.getDatabase(application).resultDao()
        repository = ResultRepository(resultDao)
        readAllData = repository.readAllData
    }

    fun addResult(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addResult(result)
        }
    }

    fun deleteAllResult() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllResult()
        }
    }
}