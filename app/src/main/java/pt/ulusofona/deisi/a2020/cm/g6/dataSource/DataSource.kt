package pt.ulusofona.deisi.a2020.cm.g6.dataSource

import pt.ulusofona.deisi.a2020.cm.g6.CovidData

class DataSource {
    private var dataPublicacao: String = "01-12-2021"
    private var confirmados: Int = 818787;
    private var confirmados_arsnorte: Int = 329797
    private var confirmados_arscentro: Int = 116814
    private var confirmados_arslvt: Int = 310261
    private var confirmados_arsalentejo: Int = 28936
    private var confirmados_arsalgarve: Int = 20550
    private var confirmados_acores: Int = 3994
    private var confirmados_madeira: Int = 8435
    private var confirmados_novos: Int = 575
    private var recuperados: Int = 769838
    private var obitos: Int = 16805
    private var internados: Int = 712
    private var internados_uci: Int = 712
    private var novos_obitos: Int = 156
    private var novos_recuperados: Int = 147
    private var novos_internados: Int = 12

    fun getData(){
        CovidData.confirmados = this.confirmados
        CovidData.dataPublicacao = this.dataPublicacao
        CovidData.confirmados_arsnorte = this.confirmados_arsnorte
        CovidData.confirmados_arscentro = this.confirmados_arscentro
        CovidData.confirmados_arslvt = this.confirmados_arslvt
        CovidData.confirmados_arsalentejo = this.confirmados_arsalentejo
        CovidData.confirmados_arsalgarve = this.confirmados_arsalgarve
        CovidData.confirmados_acores = this.confirmados_acores
        CovidData.confirmados_madeira = this.confirmados_madeira
        CovidData.confirmados_novos = this.confirmados_novos
        CovidData.obitos = this.obitos
        CovidData.internados = this.internados
        CovidData.recuperados = this.recuperados
        CovidData.internados_uci = this.internados_uci
        CovidData.novos_internados = this.novos_internados
        CovidData.novos_obitos = this.novos_obitos
        CovidData.novos_recuperados = this.recuperados
        CovidData.novos_recuperados = this.novos_recuperados
    }
}