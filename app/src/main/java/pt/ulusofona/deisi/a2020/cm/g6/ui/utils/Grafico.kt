package pt.ulusofona.deisi.a2020.cm.g6.ui.utils

class Grafico() {
    var maxConfirmados: Int = 0
    var maxRecuperados: Int  = 0
    var maxInternados: Int = 0
    var maxObitos: Int = 0
    var valuesConfirmados: List<Int> = mutableListOf()
    var valuesRecuperados: List<Int> = mutableListOf()
    var valuesObitos: List<Int> = mutableListOf()
    var valuesInternados: List<Int> = mutableListOf()
    var fromToday: Boolean = false
}