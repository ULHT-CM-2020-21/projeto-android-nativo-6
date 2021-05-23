package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.CovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.domain.graficos.GraficosLogic
import pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard.ENDPOINT
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchGraficoListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.GraficoUIListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico


class GraficosViewModel (application: Application): AndroidViewModel(application), FetchGraficoListener{

    private val storage = CovidDatabase.getInstance(application).operationDao()
    private val repository =  CovidRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val graficosLogic = GraficosLogic(repository)
    private var listeners = mutableListOf<GraficoUIListener>()


    fun registerViewListener(listener: GraficoUIListener){
        listeners.add(listener)
    }

    fun unregisterViewListener(listener: GraficoUIListener){
        listeners.remove(listener)
    }

    fun notifyListener(grafico: Grafico){
        for(i in listeners){
            i.onUpdateUI(grafico)
        }
    }

    fun drawGraphs(){
        graficosLogic.registerListener(this)
        CoroutineScope(Dispatchers.IO).launch{
            graficosLogic.askGraphsData()
        }
    }

    override fun onDataFetched(grafico: Grafico) {
        CoroutineScope(Dispatchers.Main).launch {
            notifyListener(grafico)
        }
    }

}