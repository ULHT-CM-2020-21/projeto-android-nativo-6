package pt.ulusofona.deisi.a2020.cm.g6.data.remote.responses

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


    @SerializedName("data") var data: LinkedTreeMap<String, String> =  LinkedTreeMap()

    @SerializedName("confirmados")
    var confirmados:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("recuperados")
    var recuperados:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("obitos")
    var obitos:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("internados")
    var internados:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_arsnorte")
    var confirmados_arsnorte:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_arscentro")
    var confirmados_arscentro:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_arslvt")
    var confirmados_arslvt:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_arsalentejo")
    var confirmados_arsalentejo:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_arsalgarve")
    var confirmados_arsalgarve:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_acores")
    var confirmados_acores:  LinkedTreeMap<String, Int> =  LinkedTreeMap()

    @SerializedName("confirmados_madeira")
    var confirmados_madeira:  LinkedTreeMap<String, Int> =  LinkedTreeMap()




}