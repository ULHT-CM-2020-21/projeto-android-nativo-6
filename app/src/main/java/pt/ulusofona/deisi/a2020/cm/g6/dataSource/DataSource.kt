package pt.ulusofona.deisi.a2020.cm.g6.dataSource

import pt.ulusofona.deisi.a2020.cm.g6.CovidData

class DataSource {

    private var confirmados: Int = 819210;

    private var confirmados_arsnorte: Int = 329923
    private var confirmados_arscentro: Int = 116873
    private var confirmados_arslvt: Int = 310443
    private var confirmados_arsalentejo: Int = 28953
    private var confirmados_arsalgarve: Int = 20572
    private var confirmados_acores: Int = 4003
    private var confirmados_madeira: Int = 8443

    private var confirmados_novos: Int = 575
    private var recuperados: Int = 769838
    private var obitos: Int = 16805
    private var internados: Int = 712

    private var internados_uci: Int = 712

    //Estas variaveis nao estao na base de dados, mas podem ser calculadas com os dados presentes na mesma
    private var novos_obitos: Int = 156
    private var novos_recuperados: Int = 147
    private var novos_internados: Int = 12

    private var novos_confirmados_arsnorte: Int = 120
    private var novos_confirmados_arscentro: Int = 53
    private var novos_confirmados_arslvt: Int = 210
    private var novos_confirmados_arsalentejo: Int = 8
    private var novos_confirmados_arsalgarve: Int = 10
    private var novos_confirmados_acores: Int = 12
    private var novos_confirmados_madeira: Int = 15


    fun getConfirmados(data: String): Int {
        return confirmados;
    }

    fun getConfirmados_arsnorte(data: String): Int {
        return confirmados_arsnorte;
    }

    fun getConfirmados_arscentro(data: String): Int {
        return confirmados_arscentro;
    }

    fun getConfirmados_arslvt(data: String): Int {
        return confirmados_arslvt;
    }

    fun getConfirmados_arsalentejo(data: String): Int {
        return confirmados_arsalentejo;
    }

    fun getConfirmados_arsalgarve(data: String): Int {
        return confirmados_arsalgarve;
    }

    fun getConfirmados_madeira(data: String): Int {
        return confirmados_madeira;
    }

    fun getConfirmados_acores(data: String): Int {
        return confirmados_acores;
    }

    fun getConfirmados_novos(data: String): Int {
        return confirmados_novos;
    }

    fun getInternados_uci(data: String): Int {
        return internados_uci;
    }

    fun getRecuperados(data: String): Int {
        return recuperados;
    }

    fun getObitos(data: String): Int {
        return obitos;
    }

    fun getInternados(data: String): Int {
        return internados;
    }

    fun getNovos_obitos(data: String): Int {
        return novos_obitos;
    }

    fun getNovos_recuperados(data: String): Int {
        return novos_recuperados;
    }

    fun getNovos_internados(data: String): Int {
        return novos_internados;
    }

    fun getNovos_confirmados_arsnorte(data: String): Int {
        return novos_confirmados_arsnorte;
    }
    fun getNovos_confirmados_arscentro(data: String): Int {
        return novos_confirmados_arscentro;
    }
    fun getNovos_confirmados_arslvt(data: String): Int {
        return novos_confirmados_arslvt;
    }
    fun getNovos_confirmados_arsalentejo(data: String): Int {
        return novos_confirmados_arsalentejo;
    }
    fun getNovos_confirmados_arsalgarve(data: String): Int {
        return novos_confirmados_arsalgarve;
    }
    fun getNovos_confirmados_acores(data: String): Int {
        return novos_confirmados_acores;
    }
    fun getNovos_confirmados_madeira(data: String): Int {
        return novos_confirmados_madeira;
    }




}