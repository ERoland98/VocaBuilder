package hu.bme.aut.android.vocabuilder.database.result

import androidx.lifecycle.LiveData

/*
The class abstracts to multiple data sources.
 */
class ResultRepository(private val resultDao: ResultDao) {
    val readAllData: LiveData<List<Result>> = resultDao.getAll()

    fun addResult(result: Result) {
        resultDao.insert(result)
    }

    fun deleteAllResult() {
        resultDao.deleteAll()
    }
}