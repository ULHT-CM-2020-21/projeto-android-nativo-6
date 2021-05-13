package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.dashboard


import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.CovidData
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils.Regiao


class DashboardLogic {

    private var covidHoje: CovidData = CovidData()

    var regiaoNorte = Regiao(
        "Norte",
        String.format("%,d", covidHoje.confirmados_arsnorte),
        String.format("%,d", covidHoje.novos_confirmados_arsnorte),
        ""
    )
    var regiaoCentro = Regiao(
        "Centro",
        String.format("%,d", covidHoje.confirmados_arscentro),
        String.format("%,d", covidHoje.novos_confirmados_arscentro),
        ""
    )
    var regiaoLVT = Regiao(
        "Lisboa e Vale Do Tejo",
        String.format("%,d", covidHoje.confirmados_arslvt),
        String.format("%,d", covidHoje.novos_confirmados_arslvt),
        ""
    )
    var regiaoAlentejo = Regiao(
        "Alentejo",
        String.format("%,d", covidHoje.confirmados_arsalentejo),
        String.format("%,d", covidHoje.novos_confirmados_arsalentejo),
        ""
    )
    var regiaoAlgarve = Regiao(
        "Algarve",
        String.format("%,d", covidHoje.confirmados_arsalgarve),
        String.format("%,d", covidHoje.novos_confirmados_arsalgarve),
        ""
    )
    var regiaoAcores = Regiao(
        "Açores",
        String.format("%,d", covidHoje.confirmados_acores),
        String.format("%,d", covidHoje.novos_confirmados_acores),
        ""
    )
    var regiaomadeira = Regiao(
        "Madeira",
        String.format("%,d", covidHoje.confirmados_madeira),
        String.format("%,d", covidHoje.novos_confirmados_madeira),
        ""
    )


    fun getNumeroInternados(): Int {
        return covidHoje.internados
    }

    fun getNumeroConfirmados(): Int {
        return covidHoje.confirmados
    }

    fun getNumeroObitos(): Int {
        return covidHoje.obitos
    }

    fun getNumeroRecuperados(): Int {
        return covidHoje.recuperados
    }

    fun getNumeroNovosConfirmados(): Int {
        return covidHoje.confirmados_novos
    }

    fun getNumeroNovosInternados(): Int {
        return covidHoje.novos_internados
    }

    fun getNumeroNovosObitos(): Int {
        return covidHoje.novos_obitos
    }

    fun getNumeroNovosRecuperados(): Int {
        return covidHoje.novos_recuperados
    }

    fun getNumeroCasosTotaisRN(): String {
        return regiaoNorte.casosTotais
    }

    fun getNumeroCasosUltimaRN(): String {
        return regiaoNorte.casosUltima
    }

    fun getNumeroCasosTotaisRC(): String {
        return regiaoCentro.casosTotais
    }

    fun getNumeroCasosUltimasRC(): String {
        return regiaoCentro.casosUltima
    }

    fun getNumeroCasosTotaisLVT(): String {
        return regiaoLVT.casosTotais
    }

    fun getNumeroCasosUltimasLV(): String {
        return regiaoLVT.casosUltima
    }

    fun getNumeroCasosTotaisAlentejo(): String {
        return regiaoAlentejo.casosTotais
    }

    fun getNumeroCasosUltimasAlentejo(): String {
        return regiaoAlentejo.casosUltima
    }

    fun getNumeroCasosTotaisAlgarve(): String {
        return  regiaoAlgarve.casosTotais
    }

    fun getNumeroCasosUltimasAlgarve(): String {
        return regiaoAlgarve.casosUltima
    }

    fun getNumeroCasosTotaisMadeira(): String {
        return regiaomadeira.casosTotais
    }

    fun getNumeroCasosUltimasMadeira(): String {
        return regiaomadeira.casosUltima
    }

    fun getNumeroCasosTotaisAcores(): String {
        return regiaoAcores.casosTotais
    }

    fun getNumeroCasosUltimasAcores(): String {
        return regiaoAcores.casosUltima
    }


}