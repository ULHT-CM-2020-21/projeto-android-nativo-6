package pt.ulusofona.deisi.a2020.cm.g6.domain.registo

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.TesteCovidRepositry

class RegistoLogic (private val repository: TesteCovidRepositry) {


    fun addTeste(testeCovid: TesteCovid){
        repository.saveTesteCovid(testeCovid)
    }
}