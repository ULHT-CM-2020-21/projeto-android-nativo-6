package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

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

                        val numberCovidTotal = daysBetween(getDaysAgo(i)).toString()

                        covidManyDays.confirmadosTotais =
                            response.body()?.confirmados?.get(numberCovidTotal)!!
                        covidManyDays.internadosTotais =
                            response.body()?.internados?.get(numberCovidTotal)!!
                        covidManyDays.obitosTotais =
                            response.body()?.obitos?.get(numberCovidTotal)!!
                        covidManyDays.recuperadosTotais =
                            response.body()?.recuperados?.get(numberCovidTotal)!!

                        covidManyDays.norteTotal =
                            response.body()?.confirmados_arsnorte?.get(numberCovidTotal)!!
                        covidManyDays.centroTotal =
                            response.body()?.confirmados_arscentro?.get(numberCovidTotal)!!
                        covidManyDays.lisboaTotal =
                            response.body()?.confirmados_arslvt?.get(numberCovidTotal)!!
                        covidManyDays.alentejoTotal =
                            response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)!!
                        covidManyDays.algarveTotal =
                            response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)!!
                        covidManyDays.acoresTotal =
                            response.body()?.confirmados_acores?.get(numberCovidTotal)!!
                        covidManyDays.madeiraTotal =
                            response.body()?.confirmados_madeira?.get(numberCovidTotal)!!

                        local.insert(covidManyDays)
                    }
                }
            }
            for (i in 15 downTo 0) {
                var atualCovid = local.getByDate(getDaysAgo(i))
                var antigoCovid = local.getByDate(getDaysAgo(i + 1))
                if (atualCovid != null && antigoCovid != null) {
                    atualCovid = calcularDadosCovidEntreDatas(atualCovid!!, antigoCovid!!)
                    local.updateByDate24h(
                        atualCovid.confirmados24,
                        atualCovid.recuperados24,
                        atualCovid.internados24,
                        atualCovid.obitos24,
                        atualCovid.data
                    )
                }
            }
            var covidHoje = local.getByDate(getDaysAgo(0))
            if (covidHoje == null) {
                val response = service.getDadosDiaEspecifico(getDaysAgo(0))
                if (response.isSuccessful) {

                    var covidManyDays = Covid(getDaysAgo(0))

                    val numberCovidTotal = daysBetween(getDaysAgo(0)).toString()

                    covidManyDays.confirmadosTotais =
                        response.body()?.confirmados?.get(numberCovidTotal)!!
                    covidManyDays.internadosTotais =
                        response.body()?.internados?.get(numberCovidTotal)!!
                    covidManyDays.obitosTotais = response.body()?.obitos?.get(numberCovidTotal)!!
                    covidManyDays.recuperadosTotais =
                        response.body()?.recuperados?.get(numberCovidTotal)!!

                    covidManyDays.norteTotal =
                        response.body()?.confirmados_arsnorte?.get(numberCovidTotal)!!
                    covidManyDays.centroTotal =
                        response.body()?.confirmados_arscentro?.get(numberCovidTotal)!!
                    covidManyDays.lisboaTotal =
                        response.body()?.confirmados_arslvt?.get(numberCovidTotal)!!
                    covidManyDays.alentejoTotal =
                        response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)!!
                    covidManyDays.algarveTotal =
                        response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)!!
                    covidManyDays.acoresTotal =
                        response.body()?.confirmados_acores?.get(numberCovidTotal)!!
                    covidManyDays.madeiraTotal =
                        response.body()?.confirmados_madeira?.get(numberCovidTotal)!!

                    var antigoCovid = local.getByDate(getDaysAgo(1))
                    covidManyDays = calcularDadosCovidEntreDatas(covidManyDays, antigoCovid!!)
                    local.updateByDate24h(
                        covidManyDays.confirmados24,
                        covidManyDays.recuperados24,
                        covidManyDays.internados24,
                        covidManyDays.obitos24,
                        covidManyDays.data
                    )


                    var grafico = Grafico()
                    var listaConfirmados = mutableListOf<Int>()
                    var listaRecuperados = mutableListOf<Int>()
                    var listaInternados = mutableListOf<Int>()
                    var listaObitos = mutableListOf<Int>()

                    for (i in 0..14) {
                        var covid = local.getByDate(getDaysAgo(i))
                        listaConfirmados.add(covid!!.confirmados24)
                        listaRecuperados.add(covid!!.recuperados24)
                        if (covid!!.internados24 < 0) {
                            listaInternados.add(0)
                        } else {
                            listaInternados.add(covid!!.internados24)
                        }
                        listaObitos.add(covid!!.obitos24)
                    }

                    grafico.fromToday = true

                    grafico.valuesConfirmados = listaConfirmados
                    grafico.valuesRecuperados = listaRecuperados
                    grafico.valuesInternados = listaInternados
                    grafico.valuesObitos = listaObitos

                    grafico.maxConfirmados = getMaxList(listaConfirmados)
                    grafico.maxRecuperados = getMaxList(listaRecuperados)
                    grafico.maxInternados = getMaxList(listaInternados)
                    grafico.maxObitos = getMaxList(listaObitos)

                    listenerGraficos.onFetchedRepository(grafico)
                } else {
                    var grafico = Grafico()
                    var listaConfirmados = mutableListOf<Int>()
                    var listaRecuperados = mutableListOf<Int>()
                    var listaInternados = mutableListOf<Int>()
                    var listaObitos = mutableListOf<Int>()

                    for (i in 1..15) {
                        var covid = local.getByDate(getDaysAgo(i))
                        listaConfirmados.add(covid!!.confirmados24)
                        listaRecuperados.add(covid!!.recuperados24)
                        if (covid!!.internados24 < 0) {
                            listaInternados.add(0)
                        } else {
                            listaInternados.add(covid!!.internados24)
                        }
                        listaObitos.add(covid!!.obitos24)
                    }

                    grafico.fromToday = false

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

    fun getMaxList(lista: List<Int>): Int {
        var max = 0;
        for (i in lista) {
            if (i > max) {
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
                    println(numberCovidTotal)
                    println(response)
                    println(response.body()?.confirmados)
                    println(numberCovidTotal)
                    println(response.body()?.confirmados?.get(numberCovidTotal)!!)

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

                    println(numberCovidTotal)
                    println(response)
                    println(response.body()?.confirmados)
                    println(numberCovidTotal)
                    println(response.body()?.confirmados?.get(numberCovidTotal)!!)
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
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        cal2.setTime(sdf.parse(date))


        cal1[2020, Calendar.FEBRUARY] = 26

        val millis1 = cal1.timeInMillis
        val millis2 = cal2.timeInMillis
        val diff = millis2 - millis1

        val diffDays = diff / (24 * 60 * 60 * 1000)
        return diffDays + 1
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
