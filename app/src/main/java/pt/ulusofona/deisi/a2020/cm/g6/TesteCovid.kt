package pt.ulusofona.deisi.a2020.cm.g6

import android.app.DatePickerDialog
import java.io.File
import java.util.*

class TesteCovid (
    var local: String = "",
    var data: String = "DD/MM/YYYY",
    var resultadoTesteCovid: Boolean = false,
    val fotoPath: String = "/drawable/teste_negativo",
    val foto: File= File(fotoPath)){

    override fun toString(): String {
        return "TesteCovid(local='$local', data='$data', resultadoTesteCovid=$resultadoTesteCovid, fotoPath='$fotoPath', foto=$foto)"
    }
}
