package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.registo

import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories.TesteCovidRepositry

class RegistoLogic (private val repository: TesteCovidRepositry) {

    private var testeCovid: TesteCovid? = null

    fun addTeste(testeCovid: TesteCovid){
        repository.saveTesteCovid(testeCovid)
    }



}