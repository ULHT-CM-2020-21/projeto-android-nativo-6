package pt.ulusofona.deisi.a2020.cm.g6.domain.listaTestes

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.TesteCovidRepositry
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchListaListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryListaListener
import java.text.SimpleDateFormat
import java.util.*

class ListaTestesLogic(private val repository: TesteCovidRepositry): FetchRepositoryListaListener {

    var listeners = mutableListOf<FetchListaListener>()



    fun askListaData(){
        repository.getListTestesCovid(this)
    }

    fun registerListener(listener: FetchListaListener){
        listeners.add(listener)
    }

    fun unregisterListener(listener: FetchListaListener){
        listeners.remove(listener)
    }

    fun notifyListener(listaTestes: List<TesteCovid>){
        for(i in listeners){
            i.onDataFetched(listaTestes)
        }
    }

    override fun onFetchedRepository(listaTestes: List<TesteCovid>) {
        notifyListener(listaTestes)
    }

}