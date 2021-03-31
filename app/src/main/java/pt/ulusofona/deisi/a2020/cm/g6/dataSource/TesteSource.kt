package pt.ulusofona.deisi.a2020.cm.g6.dataSource

import pt.ulusofona.deisi.a2020.cm.g6.TesteCovid

object TesteSource{
    var listTestes =mutableListOf(TesteCovid("Farmacia Default 1","12-11-2021",true,""),TesteCovid("Farmacia Default 2","11-01-2020",false,""))

    fun addTest(test: TesteCovid){
        listTestes.add(test)
    }

    fun getAllTeste(): MutableList<TesteCovid> {
        return listTestes
    }
    init {

    }
}