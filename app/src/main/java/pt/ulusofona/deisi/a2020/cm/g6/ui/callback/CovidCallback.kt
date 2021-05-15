package pt.ulusofona.deisi.a2020.cm.g6.ui.callback

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid

interface CovidCallback {
    fun getDados(covidData: Covid)
}