package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.services


import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.responses.CovidResponse
import retrofit2.Response
import retrofit2.http.GET

interface CovidService {

    @GET("get_last_update")
    suspend fun getDadosDia(): Response<CovidResponse>
}