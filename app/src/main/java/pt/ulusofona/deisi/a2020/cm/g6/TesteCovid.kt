package pt.ulusofona.deisi.a2020.cm.g6

import java.io.File
import java.util.*

class TesteCovid (
    val local: String = "",
    val data: Calendar = Calendar.getInstance(),
    val resultadoTesteCovid: Boolean = false,
    val fotoPath: String = "/drawable/teste_negativo",
    val foto: File= File(fotoPath))
