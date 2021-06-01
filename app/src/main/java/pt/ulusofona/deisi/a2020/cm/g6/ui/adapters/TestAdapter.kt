package pt.ulusofona.deisi.a2020.cm.g6.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.teste_item_expression.view.*
import org.w3c.dom.Text
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid


class TestAdapter(
    private val context: Context,
    private val layout: Int,
    private val items: MutableList<TesteCovid>
) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val local: TextView = view.localTeste
        val resultado: TextView = view.resultadoTesteOutput
        val temFoto: TextView = view.fotoOutput
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))

    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.local.text = items[position].local

        if (items[position].resultadoTesteCovid) {
            holder.resultado.text = context.getString(R.string.resultadoTestePositivo)
        } else {
            holder.resultado.text = context.getString(R.string.resultadoTesteNegativo)
        }

        if (items[position].temFoto) {
            holder.temFoto.text = context.getString(R.string.temFoto)
        } else {
            holder.temFoto.text = context.getString(R.string.semFoto)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }


}