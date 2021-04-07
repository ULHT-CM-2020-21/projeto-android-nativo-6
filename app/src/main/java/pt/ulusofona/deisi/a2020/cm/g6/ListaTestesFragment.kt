package pt.ulusofona.deisi.a2020.cm.g6


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lista_testes.*
import pt.ulusofona.deisi.a2020.cm.g6.dataSource.TesteSource
import java.util.*


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
        val listaOrdenada = TesteSource.getAllTeste()
        var imagemOrdenar = true

        /*
        testAdapter = TestAdapter(context as MainActivity, R.layout.teste_item_expression, list as ArrayList<TesteCovid>)
        list_test.adapter = testAdapter
        list_test.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //navegar como nao sei?
        })
        */
        list_test.layoutManager = LinearLayoutManager(context as MainActivity)
        list_test.adapter = TestAdapter(
            context as MainActivity,
            R.layout.teste_item_expression,
            listaOrdenada as ArrayList<TesteCovid>
        )
        list_test.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                list_test,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        activity?.let {
                            NavigationManager.goToTest(
                                it.supportFragmentManager, listaOrdenada.elementAt(
                                    position
                                )
                            )
                        }
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // do whatever
                    }
                })
        )




        ordenar_lista.setOnClickListener {
            listaOrdenada.reverse()
            list_test.layoutManager = LinearLayoutManager(context as MainActivity)
            list_test.adapter = TestAdapter(
                context as MainActivity,
                R.layout.teste_item_expression,
                listaOrdenada
            )
            if (imagemOrdenar) {
                ordenar_lista.setImageResource(R.drawable.ordenar_crescente)
                imagemOrdenar = false
            }else{
                ordenar_lista.setImageResource(R.drawable.ordenar_decrescente)
                imagemOrdenar = true
            }
        }

    }

//    fun ordenarAoContrario(listaParaOrdenar: MutableList<TesteCovid>) {
//        listaParaOrdenar.reverse()
//        for (i in listaParaOrdenar)
//            println(i)
//    }

}