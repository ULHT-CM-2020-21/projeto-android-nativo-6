package pt.ulusofona.deisi.a2020.cm.g6



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.teste_item_expression.view.*
import java.util.ArrayList


class TestAdapter(context: MainActivity, private val layout: Int, items: ArrayList<TesteCovid>) : ArrayAdapter<TesteCovid> (context,layout,items) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItem(position: Int): TesteCovid? {
        return super.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layout,parent,false)
        view.localTeste?.text = getItem(position)?.local
        view.data?.text = getItem(position)?.data
        return view
    }

}