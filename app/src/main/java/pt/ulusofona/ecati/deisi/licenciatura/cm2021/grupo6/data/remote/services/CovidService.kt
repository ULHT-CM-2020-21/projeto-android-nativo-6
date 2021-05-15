package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.services


import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.responses.CovidResponse
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.responses.CovidResponsePast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidService {

    @GET("get_last_update")
    suspend fun getDadosDia(): Response<CovidResponse>

    @GET("get_entry/{date}")
    suspend fun getDadosDiaEspecifico(@Path("date") data: String): Response<CovidResponsePast>
}