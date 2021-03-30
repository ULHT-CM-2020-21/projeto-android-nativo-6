package pt.ulusofona.deisi.a2020.cm.g6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_lista_testes.*
import java.util.ArrayList



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
        val list = mutableListOf(TesteCovid("Farmacia Maria Ines Abuelia Toma","12-11-2021",true,""),TesteCovid("Farmacia 2","11-01-2020",false,""))

        testAdapter = TestAdapter(context as MainActivity, R.layout.teste_item_expression, list as ArrayList<TesteCovid>)
        list_test.adapter = testAdapter
        list_test.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //navegar como nao sei?
        })

    }


}