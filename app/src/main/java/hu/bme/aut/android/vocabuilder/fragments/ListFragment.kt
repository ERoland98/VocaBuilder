package hu.bme.aut.android.vocabuilder.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.EntryViewModel
import hu.bme.aut.android.vocabuilder.dictionary.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var entryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // EntryViewModel
        entryViewModel = ViewModelProvider(this).get(EntryViewModel::class.java)
        entryViewModel.readAllData.observe(viewLifecycleOwner, {
            entry -> adapter.setData(entry)
        })

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllEntry()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllEntry() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            entryViewModel.deleteAllEntry()
            Snackbar.make(fragment_list_root, "Successfully removed all entries.", Snackbar.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete all entries")
        builder.setMessage("Are you sure you want to delete all entries?")
        builder.create().show()
    }
}