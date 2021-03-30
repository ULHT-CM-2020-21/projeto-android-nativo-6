package pt.ulusofona.deisi.a2020.cm.g6



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.regiao_item_expression.view.*
import java.util.ArrayList


class RegiaoAdapter(context: Context, private val layout: Int, items: ArrayList<Regiao>) : ArrayAdapter<Regiao> (context,layout,items) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItem(position: Int): Regiao? {
        return super.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layout,parent,false)
        view.nomeRegiao.text = getItem(position)?.nome.toString()
        view.casosTotais.text = getItem(position)?.casosTotais.toString()
        view.novosCasos.text = getItem(position)?.casosUltima.toString()
        return view
    }

}