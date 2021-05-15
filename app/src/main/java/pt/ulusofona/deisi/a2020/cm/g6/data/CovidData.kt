package pt.ulusofona.deisi.a2020.cm.g6.data

import java.text.SimpleDateFormat
import java.util.*

class CovidData {
    var dataPublicacao: String
    var confirmados: Int = 0
    var confirmados_arsnorte: Int = 0
    var confirmados_arscentro: Int = 0
    var confirmados_arslvt: Int = 0
    var confirmados_arsalentejo: Int = 0
    var confirmados_arsalgarve: Int = 0
    var confirmados_acores: Int = 0
    var confirmados_madeira: Int = 0
    var confirmados_novos: Int = 0
    var recuperados: Int = 0
    var obitos: Int = 0
    var internados: Int = 0
    var internados_uci: Int = 0
    var novos_obitos: Int = 0
    var novos_recuperados: Int = 0
    var novos_internados: Int = 0
    var novos_confirmados_arsnorte: Int = 0
    var novos_confirmados_arscentro: Int = 0
    var novos_confirmados_arslvt: Int = 0
    var novos_confirmados_arsalentejo: Int = 0
    var novos_confirmados_arsalgarve: Int = 0
    var novos_confirmados_acores: Int = 0
    var novos_confirmados_madeira: Int = 0
    var listDiasConfirmados: MutableList<Int> = mutableListOf()
    var listDiasRecuperados: MutableList<Int> = mutableListOf()
    var listDiasObitos: MutableList<Int> = mutableListOf()
    var listDiasInternados: MutableList<Int> = mutableListOf()


    constructor(data: String) : this() {
        dataPublicacao = data;
        getData()
    }

    constructor() {
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        val formatedDate = sdf.format(date)
        dataPublicacao = formatedDate
        getData()
    }

    private fun getData() {
        confirmados = DataSource().getConfirmados(dataPublicacao)
        confirmados_arsnorte = DataSource()
            .getConfirmados_arsnorte(dataPublicacao)
        confirmados_arscentro = DataSource()
            .getConfirmados_arscentro(dataPublicacao)
        confirmados_arslvt = DataSource()
            .getConfirmados_arslvt(dataPublicacao)
        confirmados_arsalentejo = DataSource()
            .getConfirmados_arsalentejo(dataPublicacao)
        confirmados_arsalgarve = DataSource()
            .getConfirmados_arsalgarve(dataPublicacao)
        confirmados_acores = DataSource()
            .getConfirmados_acores(dataPublicacao)
        confirmados_madeira = DataSource()
            .getConfirmados_madeira(dataPublicacao)
        confirmados_novos = DataSource()
            .getConfirmados_novos(dataPublicacao)
        recuperados = DataSource().getRecuperados(dataPublicacao)
        obitos = DataSource().getObitos(dataPublicacao)
        internados = DataSource().getInternados(dataPublicacao)
        internados_uci = DataSource().getInternados_uci(dataPublicacao)
        novos_obitos = DataSource().getNovos_obitos(dataPublicacao)
        novos_recuperados = DataSource()
            .getNovos_recuperados(dataPublicacao)
        novos_internados = DataSource()
            .getNovos_internados(dataPublicacao)
        novos_confirmados_arsnorte = DataSource()
            .getNovos_confirmados_arsnorte(dataPublicacao)
        novos_confirmados_arscentro = DataSource()
            .getNovos_confirmados_arscentro(dataPublicacao)
        novos_confirmados_arslvt = DataSource()
            .getNovos_confirmados_arslvt(dataPublicacao)
        novos_confirmados_arsalentejo = DataSource()
            .getNovos_confirmados_arsalentejo(dataPublicacao)
        novos_confirmados_arsalgarve = DataSource()
            .getNovos_confirmados_arsalgarve(dataPublicacao)
        novos_confirmados_acores = DataSource()
            .getNovos_confirmados_acores(dataPublicacao)
        novos_confirmados_madeira = DataSource()
            .getNovos_confirmados_madeira(dataPublicacao)
        listDiasConfirmados = DataSource()
            .get15DiasConfirmados(dataPublicacao)
        listDiasRecuperados = DataSource()
            .get15DiasRecuperados(dataPublicacao)
        listDiasObitos = DataSource().get15DiasObitos(dataPublicacao)
        listDiasInternados = DataSource()
            .get15DiasInternados(dataPublicacao)
    }


}