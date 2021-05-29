package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.CovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses.CovidResponsePast
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.services.CovidService
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryCovidListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryGraficoListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.InetAddress
import java.text.SimpleDateFormat
import java.util.*

class CovidRepository(private val local: CovidDao, private val remote: Retrofit) {

    private lateinit var listenerCovid: FetchRepositoryCovidListener
    private lateinit var listenerGraficos: FetchRepositoryGraficoListener
    private var daysAgoNumberFromLocal = 0

    private fun writeCovidObjectDataFromRemote(covid: Covid, numberCovidTotal: String, response: Response<CovidResponsePast>): Covid {
        val confirmadosTotais: Int? = response.body()?.confirmados?.get(numberCovidTotal)
        if (confirmadosTotais != null) {
            covid.confirmadosTotais = confirmadosTotais
        } else {
            covid.confirmadosTotais = 0
        }

        val internadosTotais: Int? = response.body()?.internados?.get(numberCovidTotal)
        if (internadosTotais != null) {
            covid.internadosTotais = internadosTotais
        } else {
            covid.internadosTotais = 0
        }

        val obitosTotais: Int? = response.body()?.obitos?.get(numberCovidTotal)
        if (obitosTotais != null) {
            covid.obitosTotais = obitosTotais
        } else {
            covid.obitosTotais = 0
        }

        val recuperadosTotais: Int? = response.body()?.recuperados?.get(numberCovidTotal)
        if (recuperadosTotais != null) {
            covid.recuperadosTotais = recuperadosTotais
        } else {
            covid.recuperadosTotais = 0
        }

        val norteTotal: Int? = response.body()?.confirmados_arsnorte?.get(numberCovidTotal)
        if (norteTotal != null) {
            covid.norteTotal = norteTotal
        } else {
            covid.norteTotal = 0
        }

        val centroTotal: Int? = response.body()?.confirmados_arscentro?.get(numberCovidTotal)
        if (centroTotal != null) {
            covid.centroTotal = centroTotal
        } else {
            covid.centroTotal = 0
        }

        val lisboaTotal: Int? = response.body()?.confirmados_arslvt?.get(numberCovidTotal)
        if (lisboaTotal != null) {
            covid.lisboaTotal = lisboaTotal
        } else {
            covid.lisboaTotal = 0
        }

        val alentejoTotal: Int? = response.body()?.confirmados_arsalentejo?.get(numberCovidTotal)
        if (alentejoTotal != null) {
            covid.alentejoTotal = alentejoTotal
        } else {
            covid.alentejoTotal = 0
        }

        val algarveTotal: Int? = response.body()?.confirmados_arsalgarve?.get(numberCovidTotal)
        if (algarveTotal != null) {
            covid.algarveTotal = algarveTotal
        } else {
            covid.algarveTotal = 0
        }

        val acoresTotal: Int? = response.body()?.confirmados_acores?.get(numberCovidTotal)
        if (acoresTotal != null) {
            covid.acoresTotal = acoresTotal
        } else {
            covid.acoresTotal = 0
        }


        val madeiraTotal: Int? = response.body()?.confirmados_madeira?.get(numberCovidTotal)
        if (madeiraTotal != null) {
            covid.madeiraTotal = madeiraTotal
        } else {
            covid.madeiraTotal = 0
        }
        return covid
    }

    private fun calculateRemote24HoursNumbers15Days() {
        for (i in daysAgoNumberFromLocal + 15 downTo daysAgoNumberFromLocal) {
            var atualCovid = local.getByDate(getDaysAgo(i))
            var antigoCovid = local.getByDate(getDaysAgo(i + 1))
            if (atualCovid != null && antigoCovid != null) {
                atualCovid = calcularDadosCovidEntreDatas(atualCovid, antigoCovid)
                local.updateByDate24h(atualCovid.confirmados24, atualCovid.recuperados24, atualCovid.internados24, atualCovid.obitos24, atualCovid.data)
            }
        }
    }

    private fun createGraficoData(): Grafico {
        var grafico = Grafico()
        var listaConfirmados = mutableListOf<Int>()
        var listaRecuperados = mutableListOf<Int>()
        var listaInternados = mutableListOf<Int>()
        var listaObitos = mutableListOf<Int>()
        for (i in daysAgoNumberFromLocal..daysAgoNumberFromLocal + 14) {
            var covid = local.getByDate(getDaysAgo(i))
            if (covid != null) {
                listaConfirmados.add(covid.confirmados24)
                listaRecuperados.add(covid.recuperados24)
                if (covid.internados24 < 0) {
                    listaInternados.add(0)
                } else {
                    listaInternados.add(covid.internados24)
                }
                listaObitos.add(covid.obitos24)
            } else {
                listaConfirmados.add(0)
                listaRecuperados.add(0)
                listaInternados.add(0)
                listaObitos.add(0)
            }
        }

        grafico.valuesConfirmados = listaConfirmados
        grafico.valuesRecuperados = listaRecuperados
        grafico.valuesInternados = listaInternados
        grafico.valuesObitos = listaObitos

        // aumento destes numeros permite uma melhor UI
        // em casos em que o maximo é por exemplo 650, mas os seus vizinhso sao 649
        // iria criar uma UI com pouca qualidade
        grafico.maxConfirmados = getMaxList(listaConfirmados) + 70
        grafico.maxRecuperados = getMaxList(listaRecuperados) + 50
        grafico.maxInternados = getMaxList(listaInternados) + 3
        grafico.maxObitos = getMaxList(listaObitos) + 5
        return grafico
    }


    private fun check15DaysData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = remote.create(CovidService::class.java)
                // confirmar se tenho na bd pelo menos 15 dias de dados
                for (i in daysAgoNumberFromLocal + 16 downTo daysAgoNumberFromLocal + 1) {
                    var dadosBD = local.getByDate(getDaysAgo(i))
                    if (dadosBD == null) {
                        val response = service.getDadosDiaEspecifico(getDaysAgo(i))
                        if (response.isSuccessful) {
                            var covidManyDays = Covid(getDaysAgo(i))
                            val numberCovidTotal = daysBetween(getDaysAgo(i)).toString()
                            covidManyDays = writeCovidObjectDataFromRemote(
                                covidManyDays,
                                numberCovidTotal,
                                response
                            )
                            local.insert(covidManyDays)
                        }
                    }
                }
                calculateRemote24HoursNumbers15Days()
                listenerGraficos.onFetchedRepository(createGraficoData())
            } catch (e: IOException) {
                calculateRemote24HoursNumbers15Days()
                listenerGraficos.onFetchedRepository(createGraficoData())
            }
        }

    }

    private fun getMaxList(lista: List<Int>): Int {
        var max = 0;
        for (i in lista) {
            if (i > max) {
                max = i
            }
        }
        return max
    }

    // Confirma que temos pelo menos 2 dias de dados para desenhar dashboard
    private fun getLastCovidData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = remote.create(CovidService::class.java)
                var bdOneDayAgo = local.getByDate(getDaysAgo(1))
                var bdTwoDayAgo = local.getByDate(getDaysAgo(2))
                if (bdOneDayAgo == null) {
                    val response = service.getDadosDiaEspecifico(getDaysAgo(1))
                    if (response.isSuccessful) {
                        var covidOneDayAgo = Covid(getDaysAgo(1))
                        val numberCovidTotal = daysBetween(getDaysAgo(1)).toString()
                        covidOneDayAgo = writeCovidObjectDataFromRemote(
                            covidOneDayAgo,
                            numberCovidTotal,
                            response
                        )
                        local.insert(covidOneDayAgo)
                    }
                }
                if (bdTwoDayAgo == null) {
                    val response = service.getDadosDiaEspecifico(getDaysAgo(2))
                    if (response.isSuccessful) {

                        var covidTwoDayAgo = Covid(getDaysAgo(2))
                        val numberCovidTotal = daysBetween(getDaysAgo(2)).toString()
                        covidTwoDayAgo = writeCovidObjectDataFromRemote(
                            covidTwoDayAgo,
                            numberCovidTotal,
                            response
                        )
                        local.insert(covidTwoDayAgo)
                    }
                }
                checkLastRemoteUpdate()
            } catch (e: IOException) {
                checkLastRemoteUpdate()
            }
        }

    }

    // confirma qual os dados mais recente das API
    private fun checkLastRemoteUpdate() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = remote.create(CovidService::class.java)
                var responseHoje = service.getDadosDia()
                if (responseHoje.isSuccessful && responseHoje.body()?.data == getDaysAgo(0)) {
                    var covidHoje = Covid(getDaysAgo(0))

                    val confirmadosTotais = responseHoje.body()?.confirmados
                    if (confirmadosTotais != null) {
                        covidHoje.confirmadosTotais = confirmadosTotais
                    }
                    val recuperadosTotais = responseHoje.body()?.recuperados
                    if (recuperadosTotais != null) {
                        covidHoje.recuperadosTotais = recuperadosTotais
                    }
                    val obitosTotais = responseHoje.body()?.obitos
                    if (obitosTotais != null) {
                        covidHoje.obitosTotais = obitosTotais
                    }
                    var internadosTotais = responseHoje.body()?.internados
                    if (internadosTotais != null) {
                        covidHoje.internadosTotais = internadosTotais
                    }

                    var norteTotal = responseHoje.body()?.confirmados_arsnorte
                    if (norteTotal != null) {
                        covidHoje.norteTotal = norteTotal
                    }
                    var centroTotal = responseHoje.body()?.confirmados_arscentro
                    if (centroTotal != null) {
                        covidHoje.centroTotal = centroTotal
                    }
                    var lisboaTotal = responseHoje.body()?.confirmados_arslvt
                    if (lisboaTotal != null) {
                        covidHoje.lisboaTotal = lisboaTotal
                    }
                    var alentejoTotal = responseHoje.body()?.confirmados_arsalentejo
                    if (alentejoTotal != null) {
                        covidHoje.alentejoTotal = alentejoTotal
                    }
                    var algarveTotal = responseHoje.body()?.confirmados_arsalgarve
                    if (algarveTotal != null) {
                        covidHoje.algarveTotal = algarveTotal
                    }
                    var madeiraTotal = responseHoje.body()?.confirmados_madeira
                    if (madeiraTotal != null) {
                        covidHoje.madeiraTotal = madeiraTotal
                    }
                    var acoresTotal = responseHoje.body()?.confirmados_acores
                    if (acoresTotal != null) {
                        covidHoje.acoresTotal = acoresTotal
                    }

                    local.insert(covidHoje)

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
            } catch (e: IOException) {
                var covidOntem = local.getByDate(getDaysAgo(daysAgoNumberFromLocal ))
                var covidDoisDias = local.getByDate(getDaysAgo(daysAgoNumberFromLocal + 1))
                if (covidOntem != null && covidDoisDias != null) {
                    covidOntem = calcularDadosCovidEntreDatas(covidOntem, covidDoisDias)
                    listenerCovid.onFetchedRepository(covidOntem)
                }else{
                    var covidOlder = local.getByDate(getDaysAgo(daysAgoNumberFromLocal))
                    if (covidOlder != null) {
                        listenerCovid.onFetchedRepository(covidOlder)
                    }
                }
            }
        }
    }

    // Calcular dados entre duas datas (Covid 24 horas)
    private fun calcularDadosCovidEntreDatas(covidAtual: Covid, covidAntigo: Covid): Covid {

        covidAtual.confirmados24 = (covidAtual.confirmadosTotais.minus(covidAntigo.confirmadosTotais))
        covidAtual.internados24 = (covidAtual.internadosTotais.minus(covidAntigo.internadosTotais))
        covidAtual.obitos24 = (covidAtual.obitosTotais.minus(covidAntigo.obitosTotais))
        covidAtual.recuperados24 = (covidAtual.recuperadosTotais.minus(covidAntigo.recuperadosTotais))

        covidAtual.norte24 = (covidAtual.norteTotal.minus(covidAntigo.norteTotal))
        covidAtual.centro24 = (covidAtual.centroTotal.minus(covidAntigo.centroTotal))
        covidAtual.lisboa24 = (covidAtual.lisboaTotal.minus(covidAntigo.lisboaTotal))
        covidAtual.alentejo24 = (covidAtual.alentejoTotal.minus(covidAntigo.alentejoTotal))
        covidAtual.algarve24 = (covidAtual.algarveTotal.minus(covidAntigo.algarveTotal))
        covidAtual.acores24 = (covidAtual.acoresTotal.minus(covidAntigo.acoresTotal))
        covidAtual.madeira24 = (covidAtual.madeiraTotal.minus(covidAntigo.madeiraTotal))

        return covidAtual
    }

    fun getCovidData(callback: FetchRepositoryCovidListener) {
        listenerCovid = callback
        checkInternetAndLastLocalData()
        getLastCovidData()
    }

    private fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
    }

    private fun daysBetween(date: String): Long {
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

    fun checkInternetAndLastLocalData() {
        if (!isInternetAvailable()) {
            for (i in 0..30) {
                var covid = local.getByDate(getDaysAgo(i))
                if (covid != null) {
                    daysAgoNumberFromLocal = i
                    return
                }
            }
        }
    }

    fun isInternetAvailable(): Boolean {
        try {
            val ipAddr: InetAddress = InetAddress.getByName("google.com")
            return !ipAddr.equals("")
        } catch (e: Exception) {
            return false
        }
    }


}
