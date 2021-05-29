package pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File
import java.net.URI
import java.util.*

@Entity
data class TesteCovid (
    var local: String = "",
    var data: String = "DD/MM/YYYY",
    var resultadoTesteCovid: Boolean = false,
    var fotoPath: String? = null,
    //val foto: File= File(fotoPath), temos que ver...
    var temFoto: Boolean = false
) {

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "TesteCovid(local='$local', data='$data', resultadoTesteCovid=$resultadoTesteCovid, fotoPath='$fotoPath', temFoto='$temFoto')"
    }
}
