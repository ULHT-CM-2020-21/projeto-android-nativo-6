package pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DistritoResponse {

    @SerializedName("incidencia_risco")
    @Expose
    var incidencia_risco: String = ""
}