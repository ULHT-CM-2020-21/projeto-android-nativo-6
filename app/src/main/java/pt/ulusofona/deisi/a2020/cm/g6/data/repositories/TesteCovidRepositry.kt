package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.TesteCovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.TesteCovidCallback
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryListaListener
import java.text.SimpleDateFormat
import java.util.*


class TesteCovidRepositry(private val local: TesteCovidDao) {
    /*fun getTesteCovidData(callback: TesteCovidCallback, *//*callbackDashboard: DashboardCallback,*//* data: String){
        CoroutineScope(Dispatchers.IO ).launch {
            var listaDeTestesCovid = mutableListOf<TesteCovid>()
            val dados = local.getById(data)
            if(dados != null){

                callback.getTesteCovidData(dados)
                //preciso de callback para a lista em si? ou no detalhe do teste em si?...
            } else {

            }
        }

    }*/
    //fica para depois...

    fun getListTestesCovid(listener: FetchRepositoryListaListener) {
        listener.onFetchedRepository(ordenar(local.getAll()))
    }

    fun ordenar(listaParaOrdenar: List<TesteCovid>): List<TesteCovid> {
        val resultado = listaParaOrdenar.sortedBy { it.data.toDate() }
        return ArrayList<TesteCovid>(resultado)
    }

    fun String.toDate(): Date {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
    }

    fun saveTesteCovid(testeCovid: TesteCovid) {
        CoroutineScope(Dispatchers.IO).launch {
            local.insert(testeCovid)
        }
    }

    //#TODO APAGAR ESTE METODO
    fun deleteAllFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            local.deleteAllWARNING()
        }
    }

}