package hu.bme.aut.android.vocabuilder.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import hu.bme.aut.android.vocabuilder.database.result.ResultViewModel
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    private lateinit var entryViewModel: EntryViewModel
    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // Defining ViewModels
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        entryViewModel.readAllData.observe(this, {
            number_of_entries_dictionary.text = entryViewModel.readAllData.value?.size.toString()
        })

        resultViewModel.readAllData.observe(this, {
            number_of_quizzes_done.text = resultViewModel.readAllData.value?.size.toString()
        })
    }
}