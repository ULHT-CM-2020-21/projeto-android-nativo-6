package pt.ulusofona.deisi.a2020.cm.g6



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.regiao_item_expression.view.*
import java.util.ArrayList

// WARNING PROFESSOR - Região não implementa RecyclerView porque é uma lista com entradas ESTATICAS, são sempre as mesma regiões...
class RegiaoAdapter(context: MainActivity, private val layout: Int, items: ArrayList<Regiao>) : ArrayAdapter<Regiao> (context,layout,items) {

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
        // Visto ser uma lista fixa podemos definir manualmente cada imagem
        when (getItem(position)?.nome.toString()) {
            "Norte" ->  view.image_list.setImageResource(R.drawable.mapa_porto_norte)
            "Centro" ->  view.image_list.setImageResource(R.drawable.mapa_centro)
            "Lisboa e Vale Do Tejo" ->  view.image_list.setImageResource(R.drawable.mapa_lisboa)
            "Alentejo" ->  view.image_list.setImageResource(R.drawable.mapa_alentejo)
            "Algarve" ->  view.image_list.setImageResource(R.drawable.mapa_algarve)
            "Açores" ->  view.image_list.setImageResource(R.drawable.mapa_acores)
            "Madeira" ->  view.image_list.setImageResource(R.drawable.mapa_madeira)

        }
        return view
    }

}