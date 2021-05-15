package pt.ulusofona.deisi.a2020.cm.g6.domain.dashboard


import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.CovidData
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.CovidCallback
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.DashboardCallback
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Regiao
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

    fun getNumeroInternados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.internadosTotais
        }
    }

    fun getNumeroConfirmados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.confirmadosTotais
        }
    }

    fun getNumeroObitos(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.obitosTotais
        }
    }

    fun getNumeroRecuperados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.recuperadosTotais
        }
    }

    fun getNumeroNovosConfirmados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.confirmados24
        }
    }

    fun getNumeroNovosInternados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.internados24
        }
    }

    fun getNumeroNovosObitos(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.obitos24
        }
    }

    fun getNumeroNovosRecuperados(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.recuperados24
        }
    }

    fun getNumeroCasosTotaisRN(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.norteTotal
        }
    }

    fun getNumeroCasosUltimaRN(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.norte24
        }
    }

    fun getNumeroCasosTotaisRC(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.centroTotal
        }
    }

    fun getNumeroCasosUltimasRC(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.centro24
        }
    }

    fun getNumeroCasosTotaisLVT(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.lisboaTotal
        }
    }

    fun getNumeroCasosUltimasLV(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.lisboa24
        }
    }

    fun getNumeroCasosTotaisAlentejo(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.alentejoTotal
        }
    }

    fun getNumeroCasosUltimasAlentejo(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.alentejo24
        }
    }

    fun getNumeroCasosTotaisAlgarve(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.algarveTotal
        }
    }

    fun getNumeroCasosUltimasAlgarve(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.algarve24
        }
    }

    fun getNumeroCasosTotaisMadeira(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.madeiraTotal
        }
    }

    fun getNumeroCasosUltimasMadeira(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.madeira24
        }
    }

    fun getNumeroCasosTotaisAcores(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.acoresTotal
        }
    }

    fun getNumeroCasosUltimasAcores(): Int {
        if(covidHoje == null){
            return 0
        }else{
            return covidHoje!!.acores24
        }
    }




}