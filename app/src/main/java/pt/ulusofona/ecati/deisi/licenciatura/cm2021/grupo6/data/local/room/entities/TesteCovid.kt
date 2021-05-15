package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File
import java.util.*

@Entity
data class TesteCovid (
    var local: String = "",
    var data: String = "DD/MM/YYYY",
    var resultadoTesteCovid: Boolean = false,
    val fotoPath: String = "/drawable/teste_negativo",
    val foto: File= File(fotoPath),
    val temFoto: Boolean = true){

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "TesteCovid(local='$local', data='$data', resultadoTesteCovid=$resultadoTesteCovid, fotoPath='$fotoPath', foto=$foto, temFoto='$temFoto')"
    }
}
