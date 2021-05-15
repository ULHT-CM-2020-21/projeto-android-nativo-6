package pt.ulusofona.deisi.a2020.cm.g6.ui.callback

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid

interface TesteCovidCallback {
    fun getTesteCovidData(testeCovidData: TesteCovid)
}