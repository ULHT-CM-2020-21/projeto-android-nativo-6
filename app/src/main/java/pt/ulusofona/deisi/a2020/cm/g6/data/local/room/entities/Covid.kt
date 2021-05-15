package pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Covid(val data: String) {

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    var confirmados24: String = ""
    var confirmadosTotais: String = ""
    var recuperadosTotais: String = ""
    var recuperados24: String = ""
    var obitosTotais: String = ""
    var obitos24: String = ""
    var internadosTotais: String = ""
    var internados24: String = ""

    var norteTotal: String = ""
    var norte24: String = ""

    var centroTotal: String = ""
    var centro24: String = ""

    var lisboaTotal: String = ""
    var lisboa24: String = ""

    var alentejoTotal: String = ""
    var alentejo24: String = ""

    var algarveTotal: String = ""
    var algarve24: String = ""

    var acoresTotal: String = ""
    var acores24: String = ""

    var madeiraTotal: String = ""
    var madeira24: String = ""

    constructor(
        data: String,
        confirmadosTotais: String,
        confirmados24: String,
        recuperadosTotais: String,
        recuperados24: String,
        obitosTotais: String,
        obitos24: String,
        internadosTotais: String,
        internados24: String
    ) : this(data){
        this.confirmados24 =  confirmados24
        this.confirmadosTotais = confirmadosTotais
        this.recuperadosTotais = recuperadosTotais
        this.recuperados24 = recuperados24
        this.obitosTotais = obitosTotais
        this.obitos24 = obitos24
        this.internadosTotais = internadosTotais
        this.internados24 = internados24
    }

}