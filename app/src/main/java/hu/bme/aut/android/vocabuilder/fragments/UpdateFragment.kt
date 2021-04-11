package hu.bme.aut.android.vocabuilder.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.Entry
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var entryViewModel: EntryViewModel

    companion object {
        lateinit var language_one: String
        lateinit var word_one: String
        lateinit var language_two: String
        lateinit var word_two: String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        // Defining ViewModel
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)

        language_one = args.currentEntry.language_1
        word_one = args.currentEntry.word_1
        language_two = args.currentEntry.language_2
        word_two = args.currentEntry.word_2

        view.languageSpinner1_update.text = args.currentEntry.language_1
        view.languageEditText1_update.setText(args.currentEntry.word_1)
        view.languageSpinner2_update.text = args.currentEntry.language_2
        view.languageEditText2_update.setText(args.currentEntry.word_2)

        view.entryUpdateButton_update.setOnClickListener{
            updateDataToDatabase()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateDataToDatabase() {
        word_one = languageEditText1_update.text.toString()
        word_two = languageEditText2_update.text.toString()

        if (inputCheck(word_one, word_two)) {
            val entry = Entry(0, language_one, word_one, language_two, word_two)
            entryViewModel.updateEntry(entry)
            Snackbar.make(activity_update_root, "Successfully updated your entry!", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        else {
                Snackbar.make(activity_update_root, "The two selected languages must be different!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(word_one: String, word_two: String):Boolean {
        return !(TextUtils.isEmpty(word_one) && TextUtils.isEmpty(word_two))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteEntry()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteEntry() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            entryViewModel.deleteEntry(args.currentEntry)
            Snackbar.make(activity_update_root, "Successfully removed: ${args.currentEntry.word_1} -  ${args.currentEntry.word_2}", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete this entry")
        builder.setMessage("Are you sure you want to delete '${args.currentEntry.word_1}' -  '${args.currentEntry.word_2}'?")
        builder.create().show()
    }
}