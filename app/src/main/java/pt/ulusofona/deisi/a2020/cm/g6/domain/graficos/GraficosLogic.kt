package pt.ulusofona.deisi.a2020.cm.g6.domain.graficos

import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchGraficoListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryGraficoListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico
import java.text.SimpleDateFormat
import java.util.*

class GraficosLogic(private val repository: CovidRepository) : FetchRepositoryGraficoListener{

    var listeners = mutableListOf<FetchGraficoListener>()

    fun registerListener(listener: FetchGraficoListener){
        listeners.add(listener)
    }

    fun unregisterListener(listener: FetchGraficoListener){
        listeners.remove(listener)
    }

    fun notifyListener(grafico: Grafico){
        for(i in listeners){
            i.onDataFetched(grafico)
        }
    }

    fun askGraphsData(){
        repository.get15DaysDataGraph(this)
    }

    override fun onFetchedRepository(grafico: Grafico) {
        notifyListener(grafico)
    }
}