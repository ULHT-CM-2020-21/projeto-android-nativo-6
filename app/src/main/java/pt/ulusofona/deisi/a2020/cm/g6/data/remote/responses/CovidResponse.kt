package pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

class CovidResponse {
    @SerializedName("data")
    var data: String = ""

    @SerializedName("confirmados")
    var confirmados: String = ""

    @SerializedName("confirmados_novos")
    var confirmados_novos: String = ""

    @SerializedName("recuperados")
    var recuperados: String = ""

    @SerializedName("obitos")
    var obitos: String = ""

    @SerializedName("internados")
    var internados: String = ""

    @SerializedName("internados_uci")
    var internados_uci: String = ""

    @SerializedName("confirmados_arsnorte")
    var confirmados_arsnorte: String = ""

    @SerializedName("confirmados_arscentro")
    var confirmados_arscentro: String = ""

    @SerializedName("confirmados_arslvt")
    var confirmados_arslvt: String = ""

    @SerializedName("confirmados_arsalentejo")
    var confirmados_arsalentejo: String = ""

    @SerializedName("confirmados_arsalgarve")
    var confirmados_arsalgarve: String = ""

    @SerializedName("confirmados_acores")
    var confirmados_acores: String = ""

    @SerializedName("confirmados_madeira")
    var confirmados_madeira: String = ""






}