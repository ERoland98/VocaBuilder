package hu.bme.aut.android.vocabuilder.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.vocabuilder.R
import hu.bme.aut.android.vocabuilder.database.result.Result
import kotlinx.android.synthetic.main.custom_row_result.view.*

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var resultList = emptyList<Result>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = resultList[position]

        holder.itemView.ID_Textview.text = currentItem.id.toString()
        holder.itemView.Name_Textview.text = currentItem.name
        holder.itemView.Correct_Textview.text = currentItem.score
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun setData(entry: List<Result>) {
        this.resultList = entry
        notifyDataSetChanged()
    }
}