package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback

import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid

interface CovidCallback {
    fun getDados(covidData: Covid)
}