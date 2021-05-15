package pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Covid(val data: String) {

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    var confirmados24: Int = 0
    var confirmadosTotais: Int = 0
    var recuperadosTotais: Int = 0
    var recuperados24: Int = 0
    var obitosTotais: Int = 0
    var obitos24: Int = 0
    var internadosTotais: Int = 0
    var internados24:Int = 0

    var norteTotal: Int = 0
    var norte24: Int = 0

    var centroTotal: Int = 0
    var centro24: Int = 0

    var lisboaTotal: Int = 0
    var lisboa24:Int = 0

    var alentejoTotal: Int = 0
    var alentejo24: Int = 0

    var algarveTotal: Int = 0
    var algarve24: Int = 0

    var acoresTotal: Int = 0
    var acores24: Int = 0

    var madeiraTotal: Int = 0
    var madeira24: Int = 0

    constructor(
        data: String,
        confirmadosTotais: Int,
        confirmados24: Int,
        recuperadosTotais: Int,
        recuperados24: Int,
        obitosTotais: Int,
        obitos24: Int,
        internadosTotais: Int,
        internados24: Int
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