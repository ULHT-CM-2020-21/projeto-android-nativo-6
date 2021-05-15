package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao.CovidDao
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.services.CovidService
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.CovidCallback
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.DashboardCallback
import retrofit2.Retrofit

class CovidRepository (private val local: CovidDao, private val remote: Retrofit) {

    fun getCovidData(callback: CovidCallback, callbackDashboard: DashboardCallback,data: String){
        val service = remote.create(CovidService::class.java)
        CoroutineScope(Dispatchers.IO ).launch {
            println("DADOS")
            val response = service.getDadosDia()
            println("DADOS")
            println(response.body()?.data)
        }
        var dados = local.getByDate(data)
        if(dados != null){
            callback.getDados(dados)
            callbackDashboard.onUpdateDados()
        }

    }

}