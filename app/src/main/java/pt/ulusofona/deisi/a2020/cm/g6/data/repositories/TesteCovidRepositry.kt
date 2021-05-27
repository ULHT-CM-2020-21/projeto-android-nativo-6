package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.TesteCovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryListaListener
import java.text.SimpleDateFormat
import java.util.*


class TesteCovidRepositry(private val local: TesteCovidDao) {

    fun getListTestesCovid(listener: FetchRepositoryListaListener) {
        listener.onFetchedRepository(ordenar(local.getAll()))
    }

    private fun ordenar(listaParaOrdenar: List<TesteCovid>): List<TesteCovid> {
        val resultado = listaParaOrdenar.sortedBy { it.data.toDate() }
        return ArrayList<TesteCovid>(resultado)
    }

    private fun String.toDate(): Date {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
    }

    fun saveTesteCovid(testeCovid: TesteCovid) {
        CoroutineScope(Dispatchers.IO).launch {
            local.insert(testeCovid)
        }
    }


}