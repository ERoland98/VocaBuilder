package hu.bme.aut.android.vocabuilder.dictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.dictionary_entries.Entry
import hu.bme.aut.android.vocabuilder.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var entryList = emptyList<Entry>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = entryList[position]

        holder.itemView.first_language_Textview.text = currentItem.language_1
        holder.itemView.first_word_Textview.text = currentItem.word_1
        holder.itemView.second_language_Textview.text = currentItem.language_2
        holder.itemView.second_word_Textview.text = currentItem.word_2

        // Implementing Update feature
        holder.itemView.custom_row_root.setOnClickListener {
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem))
        }
    }

    override fun getItemCount(): Int {
        return entryList.size
    }

    fun setData(entry: List<Entry>) {
        this.entryList = entry
        notifyDataSetChanged()
    }
}