package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.dashboard


import android.os.Build
import androidx.annotation.RequiresApi
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

    fun askDataToday(callback: CovidCallback, callbackDashboard: DashboardCallback){
        CoroutineScope(Dispatchers.IO).launch {
            repository.getCovidData(callback,callbackDashboard)
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
            return covidHoje!!.obitos24
        }
    }

    fun getNumeroNovosRecuperados(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.recuperados24
        }
    }

    fun getNumeroCasosTotaisRN(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.norteTotal
        }
    }

    fun getNumeroCasosUltimaRN(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.norte24
        }
    }

    fun getNumeroCasosTotaisRC(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.centroTotal
        }
    }

    fun getNumeroCasosUltimasRC(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.centro24
        }
    }

    fun getNumeroCasosTotaisLVT(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.lisboaTotal
        }
    }

    fun getNumeroCasosUltimasLV(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.lisboa24
        }
    }

    fun getNumeroCasosTotaisAlentejo(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.alentejoTotal
        }
    }

    fun getNumeroCasosUltimasAlentejo(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.alentejo24
        }
    }

    fun getNumeroCasosTotaisAlgarve(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.algarveTotal
        }
    }

    fun getNumeroCasosUltimasAlgarve(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.algarve24
        }
    }

    fun getNumeroCasosTotaisMadeira(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.madeiraTotal
        }
    }

    fun getNumeroCasosUltimasMadeira(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.madeira24
        }
    }

    fun getNumeroCasosTotaisAcores(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.acoresTotal
        }
    }

    fun getNumeroCasosUltimasAcores(): String {
        if(covidHoje == null){
            return "-"
        }else{
            return covidHoje!!.acores24
        }
    }




}