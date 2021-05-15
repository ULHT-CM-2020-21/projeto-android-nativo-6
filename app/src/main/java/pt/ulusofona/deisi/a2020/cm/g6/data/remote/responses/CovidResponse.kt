package pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

class CovidResponse {
    @SerializedName("data")
    var data: String = ""

    @SerializedName("confirmados")
    var confirmados: Int = 0

    @SerializedName("confirmados_novos")
    var confirmados_novos: Int = 0

    @SerializedName("recuperados")
    var recuperados: Int = 0

    @SerializedName("obitos")
    var obitos: Int = 0

    @SerializedName("internados")
    var internados: Int = 0

    @SerializedName("internados_uci")
    var internados_uci: Int = 0

    @SerializedName("confirmados_arsnorte")
    var confirmados_arsnorte: Int = 0

    @SerializedName("confirmados_arscentro")
    var confirmados_arscentro: Int = 0

    @SerializedName("confirmados_arslvt")
    var confirmados_arslvt: Int = 0

    @SerializedName("confirmados_arsalentejo")
    var confirmados_arsalentejo: Int = 0

    @SerializedName("confirmados_arsalgarve")
    var confirmados_arsalgarve: Int = 0

    @SerializedName("confirmados_acores")
    var confirmados_acores: Int = 0

    @SerializedName("confirmados_madeira")
    var confirmados_madeira: Int = 0






}