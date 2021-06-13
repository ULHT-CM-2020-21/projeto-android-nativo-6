package pt.ulusofona.deisi.a2020.cm.g6.data.remote

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.services.CovidService
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchDanger


const val ENDPOINT = "https://covid19-api.vost.pt/Requests/"

class LocalizacaoRemote {

    companion object {
        lateinit var listener : FetchDanger

        fun registerListener(listener: FetchDanger){
            this.listener = listener
        }

        private fun notifyListeners(resultado: String){
            CoroutineScope(Dispatchers.Main).launch {
                listener.onfecthDanger(resultado)
            }
        }

        fun getLocationDanger(distrito: String){
            getRemoteInfo(distrito)
        }

        private fun getRemoteInfo(distrito: String){
            CoroutineScope(Dispatchers.IO).launch {
                val remote = RetrofitBuilder.getInstance(ENDPOINT)
                val service = remote.create(CovidService::class.java)
                val response = service.getDistritoInfo(distrito)
                var resultado = ""
                resultado = response.execute().body()?.get(0)?.incidencia_risco.toString()
                notifyListeners(resultado)


            }
        }

    }




}