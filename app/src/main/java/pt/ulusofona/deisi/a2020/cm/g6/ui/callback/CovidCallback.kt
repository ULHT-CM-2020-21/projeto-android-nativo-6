package pt.ulusofona.deisi.a2020.cm.g6.ui.callback

import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid

interface CovidCallback {
    fun getDados(covidData: Covid)
}