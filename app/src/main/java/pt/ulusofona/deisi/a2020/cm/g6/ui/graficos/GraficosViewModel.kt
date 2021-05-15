package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.graficos.GraficosLogic


class GraficosViewModel (application: Application): AndroidViewModel(application){
    private val graficosLogic = GraficosLogic()

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