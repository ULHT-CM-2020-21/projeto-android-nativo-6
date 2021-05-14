package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.dashboard


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.CovidData
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories.CovidRepository
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.CovidCallback
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.DashboardCallback
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils.Regiao
import java.text.SimpleDateFormat
import java.util.*


class DashboardLogic (private val repository: CovidRepository){

    private var covidHoje: Covid? = null

    fun askDataToday(callback: CovidCallback,callbackDashboard: DashboardCallback){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getCovidData(callback,callbackDashboard,"11-10-2021")
        }

    }

    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
    }

     fun setDados(covidData: Covid) {
       covidHoje = covidData
    }

    fun getNumeroInternados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.internadosTotais
        }
    }

    fun getNumeroConfirmados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.confirmadosTotais
        }
    }

    fun getNumeroObitos(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.obitosTotais
        }
    }

    fun getNumeroRecuperados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.recuperadosTotais
        }
    }

    fun getNumeroNovosConfirmados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.confirmados24
        }
    }

    fun getNumeroNovosInternados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.internados24
        }
    }

    fun getNumeroNovosObitos(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.obitosTotais
        }
    }

    fun getNumeroNovosRecuperados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.obitos24
        }
    }

    fun getNumeroCasosTotaisRN(): String {
        return "-"
    }

    fun getNumeroCasosUltimaRN(): String {
        return "-"
    }

    fun getNumeroCasosTotaisRC(): String {
        return "-"
    }

    fun getNumeroCasosUltimasRC(): String {
        return "-"
    }

    fun getNumeroCasosTotaisLVT(): String {
        return "-"
    }

    fun getNumeroCasosUltimasLV(): String {
        return "-"
    }

    fun getNumeroCasosTotaisAlentejo(): String {
        return "-"
    }

    fun getNumeroCasosUltimasAlentejo(): String {
        return "-"
    }

    fun getNumeroCasosTotaisAlgarve(): String {
        return "-"
    }

    fun getNumeroCasosUltimasAlgarve(): String {
        return "-"
    }

    fun getNumeroCasosTotaisMadeira(): String {
        return "-"
    }

    fun getNumeroCasosUltimasMadeira(): String {
        return "-"
    }

    fun getNumeroCasosTotaisAcores(): String {
        return "-"
    }

    fun getNumeroCasosUltimasAcores(): String {
        return "-"
    }




}