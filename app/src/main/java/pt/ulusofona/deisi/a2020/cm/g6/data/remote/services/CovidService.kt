package pt.ulusofona.deisi.a2020.cm.g6.data.remote.services


import pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses.CovidResponse
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses.CovidResponsePast
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses.DistritoResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidService {

    @GET("get_last_update")
    suspend fun getDadosDia(): Response<CovidResponse>

    @GET("get_entry/{date}")
    suspend fun getDadosDiaEspecifico(@Path("date") data: String): Response<CovidResponsePast>


    @GET("get_last_update_specific_county/{county}")
    fun getDistritoInfo(@Path("county") county: String): Call<List<DistritoResponse>>
}