package pt.ulusofona.deisi.a2020.cm.g6.domain.graficos

import pt.ulusofona.deisi.a2020.cm.g6.data.CovidData
import java.text.SimpleDateFormat
import java.util.*

class GraficosLogic {

    var covidHoje: CovidData = CovidData()

    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
    }



    fun getMaxConfirmados(): Int {
        var maxC = 0;
        for (i in covidHoje.listDiasConfirmados){
            if (i > maxC){
                maxC = i;
            }
        }
        return maxC
    }

    fun getMaxRecuperados(): Int {
        var maxR = 0;
        for (i in covidHoje.listDiasRecuperados){
            if (i > maxR){
                maxR = i;
            }
        }
        return maxR
    }

    fun getMaxObitos(): Int {
        var maxO = 0;
        for (i in covidHoje.listDiasObitos){
            if (i > maxO){
                maxO = i;
            }
        }
        return maxO
    }

    fun getMaxInternados(): Int {
        var maxI = 0;
        for (i in covidHoje.listDiasInternados){
            if (i > maxI){
                maxI = i;
            }
        }
        return maxI
    }

    fun getListDiasConfirmados(): MutableList<Int> {
        return covidHoje.listDiasConfirmados
    }

    fun getListDiasObitos(): MutableList<Int> {
        return covidHoje.listDiasObitos
    }

    fun getListDiasInternados(): MutableList<Int> {
        return covidHoje.listDiasInternados
    }

    fun getListDiasRecuperados(): MutableList<Int> {
        return covidHoje.listDiasRecuperados
    }

}