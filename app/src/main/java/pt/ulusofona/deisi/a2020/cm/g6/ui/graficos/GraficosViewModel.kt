package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.CovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.domain.graficos.GraficosLogic
import pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard.ENDPOINT


class GraficosViewModel (application: Application): AndroidViewModel(application){

    private val storage = CovidDatabase.getInstance(application).operationDao()
    private val repository =  CovidRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val graficosLogic = GraficosLogic(repository)

    fun setMaxConfirmados(): Int {
        return graficosLogic.getMaxConfirmados()
    }
    fun setMaxRecuperados(): Int {
        return graficosLogic.getMaxRecuperados()
    }
    fun setMaxInternados(): Int {
        return graficosLogic.getMaxInternados()
    }
    fun setMaxObitos(): Int {
        return graficosLogic.getMaxObitos()
    }

    fun onDrawGraficosConfirmados(): MutableList<Int> {
        return graficosLogic.getListDiasConfirmados()
    }
    fun onDrawGraficosRecuperados(): MutableList<Int> {
        return graficosLogic.getListDiasRecuperados()
    }
    fun onDrawGraficosObitos(): MutableList<Int> {
        return graficosLogic.getListDiasObitos()
    }
    fun onDrawGraficosInternados(): MutableList<Int> {
        return graficosLogic.getListDiasInternados()
    }

    fun getMessageToastDate(value: Int): String{
        return graficosLogic.getDaysAgo(value)
    }

    fun getGraficoDatesInfo(value: Int): String{
        return graficosLogic.getDaysAgo(value)
    }

}