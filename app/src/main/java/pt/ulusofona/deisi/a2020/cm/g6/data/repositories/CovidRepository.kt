package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.CovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.services.CovidService
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryCovidListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryGraficoListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class CovidRepository(private val local: CovidDao, private val remote: Retrofit) {

    private lateinit var listenerCovid: FetchRepositoryCovidListener
    private lateinit var listenerGraficos: FetchRepositoryGraficoListener


    fun check15DaysData() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = remote.create(CovidService::class.java)
            // confirmar se tenho na bd pelo menos 15 dias de dados
            for (i in 16 downTo 1) {

                var dadosBD = local.getByDate(getDaysAgo(i))
                if (dadosBD == null) {
                    val response = service.getDadosDiaEspecifico(getDaysAgo(i))
                    if (response.isSuccessful) {

                        var covidManyDays = Covid(getDaysAgo(i))

                        val numberCovidTotal = daysBetween(
                            getDaysAgo(i)
                        ).toString()

                        covidManyDays.confirmadosTotais = response.body()?.confirmados?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.internadosTotais = response.body()?.internados?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.obitosTotais =
                            response.body()?.obitos?.get(numberCovidTotal)!!
                        covidManyDays.recuperadosTotais = response.body()?.recuperados?.get(
                            numberCovidTotal
                        )!!

                        covidManyDays.norteTotal = response.body()?.confirmados_arsnorte?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.centroTotal = response.body()?.confirmados_arscentro?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.lisboaTotal = response.body()?.confirmados_arslvt?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.alentejoTotal = response.body()?.confirmados_arsalentejo?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.algarveTotal = response.body()?.confirmados_arsalgarve?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.acoresTotal = response.body()?.confirmados_acores?.get(
                            numberCovidTotal
                        )!!
                        covidManyDays.madeiraTotal = response.body()?.confirmados_madeira?.get(
                            numberCovidTotal
                        )!!

                        local.insert(covidManyDays)
                    }
                }
            }
            for(i in 15 downTo 1){
                var atualCovid = local.getByDate(getDaysAgo(i))
                var antigoCovid = local.getByDate(getDaysAgo(i + 1))
                atualCovid = calcularDadosCovidEntreDatas(atualCovid!!, antigoCovid!!)
                local.updateByDate24h(atualCovid.confirmados24,atualCovid.recuperados24,atualCovid.internados24,atualCovid.obitos24, atualCovid.data)
            }
            var covidHoje = local.getByDate(getDaysAgo(0))
            if (covidHoje == null) {
                val response = service.getDadosDiaEspecifico(getDaysAgo(0))
                if (response.isSuccessful) {

                    var covidManyDays = Covid(getDaysAgo(0))

                    val numberCovidTotal = daysBetween(
                        getDaysAgo(0)
                    ).toString()

                    covidManyDays.confirmadosTotais = response.body()?.confirmados?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.internadosTotais = response.body()?.internados?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.obitosTotais = response.body()?.obitos?.get(numberCovidTotal)!!
                    covidManyDays.recuperadosTotais = response.body()?.recuperados?.get(
                        numberCovidTotal
                    )!!

                    covidManyDays.norteTotal = response.body()?.confirmados_arsnorte?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.centroTotal = response.body()?.confirmados_arscentro?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.lisboaTotal = response.body()?.confirmados_arslvt?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.alentejoTotal = response.body()?.confirmados_arsalentejo?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.algarveTotal = response.body()?.confirmados_arsalgarve?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.acoresTotal = response.body()?.confirmados_acores?.get(
                        numberCovidTotal
                    )!!
                    covidManyDays.madeiraTotal = response.body()?.confirmados_madeira?.get(
                        numberCovidTotal
                    )!!
                } else {
                    var grafico = Grafico()
                    var listaConfirmados = mutableListOf<Int>()
                    var listaRecuperados = mutableListOf<Int>()
                    var listaInternados = mutableListOf<Int>()
                    var listaObitos = mutableListOf<Int>()

                    for(i in 1..15){
                        var covid = local.getByDate(getDaysAgo(i))
                        println(covid!!.data)
                        println(covid!!.confirmados24)
                        listaConfirmados.add(covid!!.confirmados24)
                        listaRecuperados.add(covid!!.recuperados24)
                        listaInternados.add(covid!!.internados24)
                        listaObitos.add(covid!!.obitos24)
                    }

                    grafico.valuesConfirmados = listaConfirmados
                    grafico.valuesRecuperados = listaRecuperados
                    grafico.valuesInternados = listaInternados
                    grafico.valuesObitos = listaObitos

                    grafico.maxConfirmados = getMaxList(listaConfirmados)
                    grafico.maxRecuperados = getMaxList(listaRecuperados)
                    grafico.maxInternados = getMaxList(listaInternados)
                    grafico.maxObitos = getMaxList(listaObitos)

                    listenerGraficos.onFetchedRepository(grafico)
                }
            }
        }

    }

    fun getMaxList(lista : List<Int>): Int {
        var max = 0;
        for (i in lista){
            if (i > max){
                max = i
            }
        }
        return max
    }

    // Confirma que temos pelo menos 2 dias de dados para desenhar dashboard
    fun checkBDLastUpdates() {
        CoroutineScope(Dispatchers.IO).launch {
            val service = remote.create(CovidService::class.java)

            var bdOneDayAgo = local.getByDate(getDaysAgo(1))
            var bdTwoDayAgo = local.getByDate(getDaysAgo(2))

            if (bdOneDayAgo == null) {
                val response = service.getDadosDiaEspecifico(getDaysAgo(1))
                if (response.isSuccessful) {

                    var covidOneDayAgo = Covid(getDaysAgo(1))
                    val numberCovidTotal = daysBetween(getDaysAgo(1)).toString()

                    covidOneDayAgo.confirmadosTotais =
                        response.body()?.confirmados?.get(numberCovidTotal)!!
                    covidOneDayAgo.recuperadosTotais =
                        response.body()?.recuperados?.get(numberCovidTotal)!!
                    covidOneDayAgo.obitosTotais = response.body()?.obitos?.get(numberCovidTotal)!!
                    covidOneDayAgo.internadosTotais =
                        response.body()?.internados?.get(numberCovidTotal)!!

                    covidOneDayAgo.norteTotal =
                        response.body()?.confirmados_arsnorte?.get(numberCovidTotal)!!
                    covidOneDayAgo.centroTotal =
                        response.body()?.confirmados_arscentro?.get(numberCovidTotal)!!
                    covidOneDayAgo.lisboaTotal =
                        response.body()?.confirmados_arslvt?.get(numberCovidTotal)!!
                    covidOneDayAgo.alentejoTotal =
                        response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)!!
                    covidOneDayAgo.algarveTotal =
                        response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)!!
                    covidOneDayAgo.madeiraTotal =
                        response.body()?.confirmados_madeira?.get(numberCovidTotal)!!
                    covidOneDayAgo.acoresTotal =
                        response.body()?.confirmados_acores?.get(numberCovidTotal)!!

                    local.insert(covidOneDayAgo)
                }
            }

            if (bdTwoDayAgo == null) {
                val response = service.getDadosDiaEspecifico(getDaysAgo(2))
                if (response.isSuccessful) {

                    var covidTwoDayAgo = Covid(getDaysAgo(2))
                    val numberCovidTotal = daysBetween(getDaysAgo(2)).toString()

                    covidTwoDayAgo.confirmadosTotais =
                        response.body()?.confirmados?.get(numberCovidTotal)!!
                    covidTwoDayAgo.recuperadosTotais =
                        response.body()?.recuperados?.get(numberCovidTotal)!!
                    covidTwoDayAgo.obitosTotais = response.body()?.obitos?.get(numberCovidTotal)!!
                    covidTwoDayAgo.internadosTotais =
                        response.body()?.internados?.get(numberCovidTotal)!!

                    covidTwoDayAgo.norteTotal =
                        response.body()?.confirmados_arsnorte?.get(numberCovidTotal)!!
                    covidTwoDayAgo.centroTotal =
                        response.body()?.confirmados_arscentro?.get(numberCovidTotal)!!
                    covidTwoDayAgo.lisboaTotal =
                        response.body()?.confirmados_arslvt?.get(numberCovidTotal)!!
                    covidTwoDayAgo.alentejoTotal =
                        response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)!!
                    covidTwoDayAgo.algarveTotal =
                        response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)!!
                    covidTwoDayAgo.madeiraTotal =
                        response.body()?.confirmados_madeira?.get(numberCovidTotal)!!
                    covidTwoDayAgo.acoresTotal =
                        response.body()?.confirmados_acores?.get(numberCovidTotal)!!

                    local.insert(covidTwoDayAgo)
                }
            }
            checkLastRemoteUpdate()
        }
    }

    // confirma qual os dados mais recente das API
    fun checkLastRemoteUpdate() {
        val service = remote.create(CovidService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var responseHoje = service.getDadosDia()
            if (responseHoje.isSuccessful && responseHoje.body()?.data == getDaysAgo(0)) {
                var covidHoje = Covid(getDaysAgo(0))
                covidHoje.confirmadosTotais = responseHoje.body()?.confirmados!!
                covidHoje.recuperadosTotais = responseHoje.body()?.recuperados!!
                covidHoje.obitosTotais = responseHoje.body()?.obitos!!
                covidHoje.internadosTotais = responseHoje.body()?.internados!!

                covidHoje.norteTotal = responseHoje.body()?.confirmados_arsnorte!!
                covidHoje.centroTotal = responseHoje.body()?.confirmados_arscentro!!
                covidHoje.lisboaTotal = responseHoje.body()?.confirmados_arslvt!!
                covidHoje.alentejoTotal = responseHoje.body()?.confirmados_arsalentejo!!
                covidHoje.algarveTotal = responseHoje.body()?.confirmados_arsalgarve!!
                covidHoje.madeiraTotal = responseHoje.body()?.confirmados_madeira!!
                covidHoje.acoresTotal = responseHoje.body()?.confirmados_acores!!

                var covidOntem = local.getByDate(getDaysAgo(1))

                if (covidOntem != null) {
                    covidHoje = calcularDadosCovidEntreDatas(covidHoje, covidOntem)
                    listenerCovid.onFetchedRepository(covidHoje)
                }


            } else {
                var covidOntem = local.getByDate(getDaysAgo(1))
                var covidDoisDias = local.getByDate(getDaysAgo(2))
                if (covidOntem != null && covidDoisDias != null) {
                    covidOntem = calcularDadosCovidEntreDatas(covidOntem, covidDoisDias)
                    listenerCovid.onFetchedRepository(covidOntem)
                }

            }
        }
    }

    // Calcular dados entre duas datas (Covid 24 horas)
    fun calcularDadosCovidEntreDatas(covidAtual: Covid, covidAntigo: Covid): Covid {

       /* println(covidAtual.data)
        println(covidAntigo.data)
        println(covidAtual.confirmadosTotais)
        println(covidAntigo.confirmadosTotais)*/
        covidAtual.confirmados24 =
            (covidAtual.confirmadosTotais?.minus(covidAntigo.confirmadosTotais)!!)
        covidAtual.internados24 =
            (covidAtual.internadosTotais?.minus(covidAntigo.internadosTotais)!!)
        covidAtual.obitos24 = (covidAtual.obitosTotais?.minus(covidAntigo.obitosTotais))!!
        covidAtual.recuperados24 =
            (covidAtual.recuperadosTotais?.minus(covidAntigo.recuperadosTotais))!!

        covidAtual.norte24 = (covidAtual.norteTotal?.minus(covidAntigo.norteTotal))!!
        covidAtual.centro24 = (covidAtual.centroTotal?.minus(covidAntigo.centroTotal))!!
        covidAtual.lisboa24 = (covidAtual.lisboaTotal?.minus(covidAntigo.lisboaTotal))!!
        covidAtual.alentejo24 = (covidAtual.alentejoTotal?.minus(covidAntigo.alentejoTotal))!!
        covidAtual.algarve24 = (covidAtual.algarveTotal?.minus(covidAntigo.algarveTotal))!!
        covidAtual.acores24 = (covidAtual.acoresTotal?.minus(covidAntigo.acoresTotal))!!
        covidAtual.madeira24 = (covidAtual.madeiraTotal?.minus(covidAntigo.madeiraTotal))!!

        /*println(covidAtual.confirmados24)
        println("--")*/
        return covidAtual
    }

    // Main function to Dashboard
    fun getCovidData(callback: FetchRepositoryCovidListener) {
        listenerCovid = callback
        checkBDLastUpdates()
    }

    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
    }

    fun daysBetween(date: String): Long {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val start: LocalDate = LocalDate.parse(date, formatter)
            val end: LocalDate = LocalDate.parse("26-02-2020", formatter)
            return ChronoUnit.DAYS.between(end, start)
        }
        return 0
    }

    fun get15DaysDataGraph(listener: FetchRepositoryGraficoListener) {
        listenerGraficos = listener
        check15DaysData()
    }


    //#TODO APAGAR ESTE METODO
    fun deleteAllFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            local.deleteAllWARNING()
        }
    }


}
