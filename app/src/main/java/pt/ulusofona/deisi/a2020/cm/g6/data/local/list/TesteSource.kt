package pt.ulusofona.deisi.a2020.cm.g6.data.local.list

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import java.text.SimpleDateFormat
import java.util.*

object TesteSource{
    var listTestes =mutableListOf(
        TesteCovid("Farmacia Default 1","12-11-2021",true,""),
        TesteCovid("Farmacia Default 2","11-1-2021",false,"")
    )

    fun addTest(test: TesteCovid){
        listTestes.add(test)
    }

    fun getAllTeste(): List<TesteCovid> {
        return ordenar(listTestes)
    }

    init {

    }

    fun ordenar(listaParaOrdenar: MutableList<TesteCovid>): List<TesteCovid> {
        val resultado = listaParaOrdenar.sortedBy { it.data.toDate()}
        return ArrayList<TesteCovid>(resultado)
    }

    fun String.toDate(): Date{
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
    }


}