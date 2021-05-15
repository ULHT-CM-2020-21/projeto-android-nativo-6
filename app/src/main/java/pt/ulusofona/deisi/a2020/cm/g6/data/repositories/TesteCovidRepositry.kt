package pt.ulusofona.deisi.a2020.cm.g6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao.TesteCovidDao
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.TesteCovidCallback


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

    fun getListTestesCovid(): List<TesteCovid> {
        return local.getAll()
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