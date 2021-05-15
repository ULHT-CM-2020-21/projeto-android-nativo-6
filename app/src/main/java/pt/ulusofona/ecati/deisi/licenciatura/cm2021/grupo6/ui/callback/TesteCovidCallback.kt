package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback

import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid

interface TesteCovidCallback {
    fun getTesteCovidData(testeCovidData: TesteCovid)
}