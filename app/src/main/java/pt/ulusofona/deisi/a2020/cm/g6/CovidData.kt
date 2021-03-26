package pt.ulusofona.deisi.a2020.cm.g6

import pt.ulusofona.deisi.a2020.cm.g6.dataSource.DataSource

object CovidData {
    var dataPublicacao: String = "01-12-2021"
    var confirmados: Int = 818787;
    var confirmados_arsnorte: Int = 329797
    var confirmados_arscentro: Int = 116814
    var confirmados_arslvt: Int = 310261
    var confirmados_arsalentejo: Int = 28936
    var confirmados_arsalgarve: Int = 20550
    var confirmados_acores: Int = 3994
    var confirmados_madeira: Int = 8435
    var confirmados_novos: Int = 575
    var recuperados: Int = 769838
    var obitos: Int = 16805
    var internados: Int = 712
    var internados_uci: Int = 712
    var novos_obitos: Int = 156
    var novos_recuperados: Int = 147
    var novos_internados: Int = 12

    init {
        DataSource().getData()
    }


}