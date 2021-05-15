package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao.TesteCovidDao
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.TesteCovidCallback


class TesteCovidRepositry(private val local: TesteCovidDao) {

    fun getTesteCovidData(callback: TesteCovidCallback, /*callbackDashboard: DashboardCallback,*/ data: String){
        CoroutineScope(Dispatchers.IO ).launch {
            var listaDeTestesCovid = mutableListOf<TesteCovid>()
            var dados = local.getById(data)
            if(dados != null){
                listaDeTestesCovid.add(dados)
                callback.getTesteCovidData(dados)
                //preciso de callback para a lista em si? ou no detalhe do teste em si?...
            } else {

            }
        }

    }

    private fun getListOperations(): List<TesteCovid> {
        return local.getAll()
    }

   /* private fun saveOperationLocal(testeCovid: TesteCovid) {
        CoroutineScope(Dispatchers.IO).launch {
            local.insert(TesteCovid(testeCovid))
        }
    }*/

    //#TODO APAGAR ESTE METODO
    fun deleteAllFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            local.deleteAllWARNING()
        }
    }

}