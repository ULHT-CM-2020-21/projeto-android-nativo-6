package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.CovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.services.CovidService
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.CovidCallback
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.DashboardCallback
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class CovidRepository(private val local: CovidDao, private val remote: Retrofit) {


    fun daysBetween(date: String): Long {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val start: LocalDate = LocalDate.parse(date, formatter)
            val end: LocalDate = LocalDate.parse("26-02-2020", formatter)
            return ChronoUnit.DAYS.between(end, start)
        }
        return 0
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

                        val numberCovidTotal = daysBetween(
                            getDaysAgo(i)
                        ).toString()

                        covidHoje.confirmadosTotais = response.body()?.confirmados?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.internadosTotais = response.body()?.internados?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.obitosTotais = response.body()?.obitos?.get(numberCovidTotal)!!
                        covidHoje.recuperadosTotais = response.body()?.recuperados?.get(
                            numberCovidTotal
                        )!!

                        covidHoje.norteTotal = response.body()?.confirmados_arsnorte?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.centroTotal = response.body()?.confirmados_arscentro?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.lisboaTotal = response.body()?.confirmados_arslvt?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.alentejoTotal = response.body()?.confirmados_arsalentejo?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.algarveTotal = response.body()?.confirmados_arsalgarve?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.acoresTotal = response.body()?.confirmados_acores?.get(
                            numberCovidTotal
                        )!!
                        covidHoje.madeiraTotal = response.body()?.confirmados_madeira?.get(
                            numberCovidTotal
                        )!!

                        local.insert(covidHoje)
                    }
                }
            }
        }

    }

    fun getCovidData(callback: CovidCallback, callbackDashboard: DashboardCallback) {
        val service = remote.create(CovidService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            deleteAllFromDB()
            check15DaysData()
            //Tento ver se ja tenho os dados mais atuais na base de dados
            var dadosBD = local.getByDate(getDaysAgo(0))
            if (dadosBD == null) {
                //Peço dados WS
                val response = service.getDadosDia()
                if (response.isSuccessful) {
                    var covidHoje = Covid(getDaysAgo(0))
                    var covidOntemdBD = local.getByDate(getDaysAgo(1))

                    covidHoje.confirmadosTotais = response.body()?.confirmados!!
                    covidHoje.recuperadosTotais = response.body()?.recuperados!!
                    covidHoje.obitosTotais = response.body()?.obitos!!
                    covidHoje.internadosTotais = response.body()?.internados!!

                    covidHoje.norteTotal = response.body()?.confirmados_arsnorte!!
                    covidHoje.centroTotal = response.body()?.confirmados_arscentro!!
                    covidHoje.lisboaTotal = response.body()?.confirmados_arslvt!!
                    covidHoje.alentejoTotal = response.body()?.confirmados_arsalentejo!!
                    covidHoje.algarveTotal = response.body()?.confirmados_arsalgarve!!
                    covidHoje.madeiraTotal = response.body()?.confirmados_madeira!!
                    covidHoje.acoresTotal = response.body()?.confirmados_acores!!

                    if (covidOntemdBD != null) {


                        var covidTotalHojeC = response.body()?.confirmados
                        var covidTotalHojeO = response.body()?.obitos
                        var covidTotalHojeR = response.body()?.recuperados
                        var covidTotalHojeI = response.body()?.internados

                        var covidTotalHojeNorte = response.body()?.confirmados_arsnorte
                        var covidTotalHojeCentro = response.body()?.confirmados_arscentro
                        var covidTotalHojeLVT = response.body()?.confirmados_arslvt
                        var covidTotalHojeAlentejo = response.body()?.confirmados_arsalentejo
                        var covidTotalHojeAlgarve = response.body()?.confirmados_arsalgarve
                        var covidTotalHojeAcores = response.body()?.confirmados_acores
                        var covidTotalHojeMadeira = response.body()?.confirmados_madeira

                        var covidOntemC = covidOntemdBD.confirmadosTotais
                        var covidOntemO = covidOntemdBD.obitosTotais
                        var covidOntemR = covidOntemdBD.recuperadosTotais
                        var covidOntemI = covidOntemdBD.internadosTotais

                        var covidOntemNorte = covidOntemdBD.norteTotal
                        var covidOntemCentro = covidOntemdBD.centroTotal
                        var covidOntemLVT = covidOntemdBD.lisboaTotal
                        var covidOntemAlentejo = covidOntemdBD.alentejoTotal
                        var covidOntemAlgarve = covidOntemdBD.algarveTotal
                        var covidOntemAcores = covidOntemdBD.acoresTotal
                        var covidOntemMadeira = covidOntemdBD.madeiraTotal

                        covidHoje.confirmados24 = (covidTotalHojeC?.minus(covidOntemC)!!)
                        covidHoje.internados24 = (covidTotalHojeI?.minus(covidOntemI)!!)
                        covidHoje.obitos24 = (covidTotalHojeO?.minus(covidOntemO))!!
                        covidHoje.recuperados24 = (covidTotalHojeR?.minus(covidOntemR))!!
                        covidHoje.norte24 = (covidTotalHojeNorte?.minus(covidOntemNorte))!!
                        covidHoje.centro24 = (covidTotalHojeCentro?.minus(covidOntemCentro))!!
                        covidHoje.lisboa24 = (covidTotalHojeLVT?.minus(covidOntemLVT))!!
                        covidHoje.alentejo24 = (covidTotalHojeAlentejo?.minus(covidOntemAlentejo))!!
                        covidHoje.algarve24 = (covidTotalHojeAlgarve?.minus(covidOntemAlgarve))!!
                        covidHoje.acores24 = (covidTotalHojeAcores?.minus(covidOntemAcores))!!
                        covidHoje.madeira24 = (covidTotalHojeMadeira?.minus(covidOntemMadeira))!!


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
