package pt.ulusofona.deisi.a2020.cm.g6.ui.listener

import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico

interface FetchGraficoListener {
    fun onDataFetched(grafico: Grafico)
}