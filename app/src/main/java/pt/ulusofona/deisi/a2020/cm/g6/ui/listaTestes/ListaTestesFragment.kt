package pt.ulusofona.deisi.a2020.cm.g6.ui.listaTestes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_lista_testes.*

import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.ui.MainActivity
import pt.ulusofona.deisi.a2020.cm.g6.ui.adapters.TestAdapter
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.RecyclerItemClickListener
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.ListaUIListener
import java.util.*


class ListaTestesFragment : Fragment(), ListaUIListener {
    private lateinit var viewModel: ListaTestesViewModel
    var imagemOrdenar = true
    private var listaDeTestes: List<TesteCovid> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_testes, container, false)
        viewModel = ViewModelProviders.of(this).get(ListaTestesViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        viewModel.drawLista()


    }

    @OnClick(R.id.ordenar_lista)
    fun ordenarLista() {
        (listaDeTestes as ArrayList<TesteCovid>).reverse()
        onUpdateUI(listaDeTestes)
    }

    override fun onUpdateUI(listaTestes: List<TesteCovid>) {
        listaDeTestes = listaTestes

        list_test.layoutManager = LinearLayoutManager(context as MainActivity)
        list_test.adapter = TestAdapter(
            context as MainActivity,
            R.layout.teste_item_expression,
            listaDeTestes as ArrayList<TesteCovid>
        )
        if (imagemOrdenar != true) {
            ordenar_lista.setImageResource(R.drawable.ordenar_crescente)
            imagemOrdenar = true
        }else{
            ordenar_lista.setImageResource(R.drawable.ordenar_decrescente)
            imagemOrdenar = false
        }
        list_test.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                list_test,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        activity?.let {
                            NavigationManager.goToTest(
                                it.supportFragmentManager, listaDeTestes.elementAt(
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
    }


}