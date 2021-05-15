package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.responses

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*


class CovidResponsePast {


    @SerializedName("data")
    var data: LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados")
    var confirmados:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("recuperados")
    var recuperados:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("obitos")
    var obitos:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("internados")
    var internados:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_arsnorte")
    var confirmados_arsnorte:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_arscentro")
    var confirmados_arscentro:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_arslvt")
    var confirmados_arslvt:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_arsalentejo")
    var confirmados_arsalentejo:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_arsalgarve")
    var confirmados_arsalgarve:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_acores")
    var confirmados_acores:  LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados_madeira")
    var confirmados_madeira:  LinkedTreeMap<String, String> =  LinkedTreeMap()




}