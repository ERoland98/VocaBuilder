package hu.bme.aut.android.vocabuilder.dictionary

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.Entry
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class Add : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var entryViewModel: EntryViewModel

    companion object {
        lateinit var language_one: String
        lateinit var word_one: String
        lateinit var language_two: String
        lateinit var word_two: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Defining ViewModel
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        // Language Spinner 1
        ArrayAdapter.createFromResource(this, R.array.languages, R.layout.spinner_item).also {
            adapter -> adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            languageSpinner1.adapter = adapter
        }
        languageSpinner1.onItemSelectedListener = this

        // Language Spinner 2
        ArrayAdapter.createFromResource(this, R.array.languages, R.layout.spinner_item).also {
                adapter -> adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            languageSpinner2.adapter = adapter
        }
        languageSpinner2.onItemSelectedListener = this

        // Submit Button
        entrySubmitButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    // Spinner - OnItemSelectedListener - onItemSelected method override
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent == languageSpinner1) {
            language_one = parent?.getItemAtPosition(position).toString()
        }
        else if (parent == languageSpinner2) {
            language_two = parent?.getItemAtPosition(position).toString()
        }
    }
    // Spinner - OnItemSelectedListener - onNothingSelected method override
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun insertDataToDatabase() {
        word_one = languageEditText1.text.toString().capitalize(Locale.getDefault())
        word_two = languageEditText2.text.toString().capitalize(Locale.getDefault())

        if (inputCheck(word_one, word_two)) {
            if (!differentLanguageCheck(language_one, language_two)) {
                val entry = Entry(0, language_one, word_one, language_two, word_two)
                entryViewModel.addEntry(entry)
                Snackbar.make(activity_add_root, "Successfully added your entry!", Snackbar.LENGTH_LONG).show()
            }
            else {
                Snackbar.make(activity_add_root, "The two selected languages must be different!", Snackbar.LENGTH_LONG).show()
            }
        }
        else {
            Snackbar.make(activity_add_root, "Please type in the words correctly!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(word_one: String, word_two: String):Boolean {
        return !(TextUtils.isEmpty(word_one) && TextUtils.isEmpty(word_two))
    }

    private fun differentLanguageCheck(language_one: String, language_two: String): Boolean {
        return language_one == language_two
    }
}