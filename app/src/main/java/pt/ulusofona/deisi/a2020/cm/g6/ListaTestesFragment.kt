package pt.ulusofona.deisi.a2020.cm.g6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_lista_testes.*
import pt.ulusofona.deisi.a2020.cm.g6.dataSource.TesteSource
import java.util.ArrayList
import javax.sql.DataSource


class ListaTestesFragment : Fragment() {
    private var testAdapter: TestAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_testes, container, false)
    }

    override fun onStart() {
        super.onStart()
        val list = TesteSource.getAllTeste()

        /*
        testAdapter = TestAdapter(context as MainActivity, R.layout.teste_item_expression, list as ArrayList<TesteCovid>)
        list_test.adapter = testAdapter
        list_test.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //navegar como nao sei?
        })
        */
        list_test.layoutManager = LinearLayoutManager(context as MainActivity)
        list_test.adapter = TestAdapter(context as MainActivity,R.layout.teste_item_expression,list as ArrayList<TesteCovid>)

    }


}