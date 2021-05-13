package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.teste_item_expression.view.*
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils.TesteCovid


class TestAdapter(
    private val context: Context,
    private val layout: Int,
    private val items: MutableList<TesteCovid>
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val data: TextView = view.data
        val local: TextView = view.localTeste
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(context).inflate(layout,parent,false))

    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.data.text = items[position].data
        holder.local.text = items[position].local
    }

    override fun getItemCount(): Int {
        return items.size
    }


}