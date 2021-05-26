package pt.ulusofona.deisi.a2020.cm.g6.ui.listaTestes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.TesteCovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.TesteCovidRepositry
import pt.ulusofona.deisi.a2020.cm.g6.domain.listaTestes.ListaTestesLogic
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchListaListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.ListaUIListener

class ListaTestesViewModel(application: Application) : AndroidViewModel(application),
    FetchListaListener {

    private val storage = TesteCovidDatabase.getInstance(application).testeCovidDao()
    private val repository = TesteCovidRepositry(storage)
    private val listaLogic = ListaTestesLogic(repository)
    private var listeners = mutableListOf<ListaUIListener>()

    fun drawLista() {
        listaLogic.registerListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            listaLogic.askListaData()
        }
    }


    fun registerListener(listener: ListaUIListener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: ListaUIListener) {
        listeners.remove(listener)
    }

    fun notifyListener(listaTestes: List<TesteCovid>) {
        for (i in listeners) {
            i.onUpdateUI(listaTestes)
        }
    }

    override fun onDataFetched(listaTestes: List<TesteCovid>) {
        CoroutineScope(Dispatchers.Main).launch {
            notifyListener(listaTestes)
        }
    }


}