package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao.CovidDao
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.services.CovidService
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.CovidCallback
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.DashboardCallback
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class CovidRepository(private val local: CovidDao, private val remote: Retrofit) {


    fun daysBetween(d: Int, m: Int, y: Int): Long {
        val end = GregorianCalendar(y, m, d).timeInMillis
        val start = GregorianCalendar(2020, 2, 26).timeInMillis
        return TimeUnit.MILLISECONDS.toDays(Math.abs(start - end)) - 1
    }

    // confirmar se tenho na bd pelo menos 15 dias de dados
    fun check15DaysData() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = remote.create(CovidService::class.java)
            // confirmar se tenho na bd pelo menos 15 dias de dados
            for (i in 1..15) {

                var dadosBD = local.getByDate(getDaysAgo(i))
                if (dadosBD == null) {
                    val response = service.getDadosDiaEspecifico(getDaysAgo(i))
                    if (response.isSuccessful) {

                        var covidHoje = Covid(getDaysAgo(i))

                        val ddmmyyArray = getDaysAgo(i).split("-").toTypedArray()
                        val numberCovidTotal = daysBetween(
                            ddmmyyArray[0].toInt(),
                            ddmmyyArray[1].toInt(),
                            ddmmyyArray[2].toInt()
                        ).toString()


                        covidHoje.confirmadosTotais =
                            response.body()?.confirmados?.get(numberCovidTotal).toString()
                        covidHoje.internadosTotais =
                            response.body()?.internados?.get(numberCovidTotal).toString()
                        covidHoje.obitosTotais =
                            response.body()?.obitos?.get(numberCovidTotal).toString()
                        covidHoje.recuperadosTotais =
                            response.body()?.recuperados?.get(numberCovidTotal).toString()

                        covidHoje.norteTotal =
                            response.body()?.confirmados_arsnorte?.get(numberCovidTotal).toString()
                        covidHoje.centroTotal =
                            response.body()?.confirmados_arscentro?.get(numberCovidTotal).toString()
                        covidHoje.lisboaTotal =
                            response.body()?.confirmados_arslvt?.get(numberCovidTotal).toString()
                        covidHoje.alentejoTotal =
                            response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)
                                .toString()
                        covidHoje.algarveTotal =
                            response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)
                                .toString()
                        covidHoje.acoresTotal =
                            response.body()?.confirmados_acores?.get(numberCovidTotal).toString()
                        covidHoje.madeiraTotal =
                            response.body()?.confirmados_madeira?.get(numberCovidTotal).toString()

                        local.insert(covidHoje)
                    }
                }
            }
        }

    }

    fun getCovidData(callback: CovidCallback, callbackDashboard: DashboardCallback) {
        val service = remote.create(CovidService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            //deleteAllFromDB()
            check15DaysData()
            //Tento ver se ja tenho os dados mais atuais na base de dados
            var dadosBD = local.getByDate(getDaysAgo(0))
            if (dadosBD == null) {
                //Peço dados WS
                val response = service.getDadosDia()
                if (response.isSuccessful) {
                    var covidHoje = Covid(getDaysAgo(0))
                    var covidOntemdBD = local.getByDate(getDaysAgo(1))

                    covidHoje.confirmadosTotais = response.body()?.confirmados.toString()
                    covidHoje.recuperadosTotais = response.body()?.recuperados.toString()
                    covidHoje.obitosTotais = response.body()?.obitos.toString()
                    covidHoje.internadosTotais = response.body()?.internados.toString()

                    covidHoje.norteTotal = response.body()?.confirmados_arsnorte.toString()
                    covidHoje.centroTotal = response.body()?.confirmados_arscentro.toString()
                    covidHoje.lisboaTotal = response.body()?.confirmados_arslvt.toString()
                    covidHoje.alentejoTotal = response.body()?.confirmados_arsalentejo.toString()
                    covidHoje.algarveTotal = response.body()?.confirmados_arsalgarve.toString()
                    covidHoje.madeiraTotal = response.body()?.confirmados_madeira.toString()
                    covidHoje.acoresTotal = response.body()?.confirmados_acores.toString()

                    if (covidOntemdBD != null) {


                        var covidTotalHojeC = response.body()?.confirmados?.toDouble()!!
                        var covidTotalHojeO = response.body()?.obitos?.toDouble()!!
                        var covidTotalHojeR = response.body()?.recuperados?.toDouble()!!
                        var covidTotalHojeI = response.body()?.internados?.toDouble()!!

                        var covidTotalHojeNorte = response.body()?.confirmados_arsnorte?.toDouble()!!
                        var covidTotalHojeCentro = response.body()?.confirmados_arscentro?.toDouble()!!
                        var covidTotalHojeLVT = response.body()?.confirmados_arslvt?.toDouble()!!
                        var covidTotalHojeAlentejo = response.body()?.confirmados_arsalentejo?.toDouble()!!
                        var covidTotalHojeAlgarve = response.body()?.confirmados_arsalgarve?.toDouble()!!
                        var covidTotalHojeAcores = response.body()?.confirmados_acores?.toDouble()!!
                        var covidTotalHojeMadeira = response.body()?.confirmados_madeira?.toDouble()!!

                        var covidOntemC = covidOntemdBD.confirmadosTotais.toDouble()
                        var covidOntemO = covidOntemdBD.obitosTotais.toDouble()
                        var covidOntemR = covidOntemdBD.recuperadosTotais.toDouble()
                        var covidOntemI = covidOntemdBD.internadosTotais.toDouble()

                        var covidOntemNorte = covidOntemdBD.norteTotal.toDouble()
                        var covidOntemCentro = covidOntemdBD.centroTotal.toDouble()
                        var covidOntemLVT = covidOntemdBD.lisboaTotal.toDouble()
                        var covidOntemAlentejo = covidOntemdBD.alentejoTotal.toDouble()
                        var covidOntemAlgarve = covidOntemdBD.algarveTotal.toDouble()
                        var covidOntemAcores = covidOntemdBD.acoresTotal.toDouble()
                        var covidOntemMadeira = covidOntemdBD.madeiraTotal.toDouble()



                        covidHoje.confirmados24 = (covidTotalHojeC - covidOntemC).toString()
                        covidHoje.internados24 = (covidTotalHojeI - covidOntemI).toString()
                        covidHoje.obitos24 = (covidTotalHojeO - covidOntemO).toString()
                        covidHoje.recuperados24 = (covidTotalHojeR - covidOntemR).toString()

                        covidHoje.norte24 = (covidTotalHojeNorte - covidOntemNorte).toString()
                        covidHoje.centro24 = (covidTotalHojeCentro - covidOntemCentro).toString()
                        covidHoje.lisboa24 = (covidTotalHojeLVT - covidOntemLVT).toString()
                        covidHoje.alentejo24 = (covidTotalHojeAlentejo - covidOntemAlentejo).toString()
                        covidHoje.algarve24 = (covidTotalHojeAlgarve - covidOntemAlgarve).toString()
                        covidHoje.acores24 = (covidTotalHojeAcores - covidOntemAcores).toString()
                        covidHoje.madeira24 = (covidTotalHojeMadeira - covidOntemMadeira).toString()



                    }

                    callback.getDados(covidHoje)
                    callbackDashboard.onUpdateDados(getDaysAgo(0))
                } else {
                    //Retorno dados de ontem se ainda nao houver dados de hoje
                    if (dadosBD != null) {
                        callback.getDados(dadosBD)
                        callbackDashboard.onUpdateDados(getDaysAgo(1))
                    }
                }
            } else {
                // ja tenho os dados de hoje nao preciso de WS
                callback.getDados(dadosBD)
                callbackDashboard.onUpdateDados(getDaysAgo(0))
            }
        }
    }


    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
    }


    //#TODO APAGAR ESTE METODO
    fun deleteAllFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            local.deleteAllWARNING()
        }
    }
}
