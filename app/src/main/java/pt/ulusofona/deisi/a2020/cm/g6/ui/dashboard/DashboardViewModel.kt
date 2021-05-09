package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import pt.ulusofona.deisi.a2020.cm.g6.domain.dashboard.DashboardLogic

class DashboardViewModel  (application: Application): AndroidViewModel(application){

    private val dashboardLogic = DashboardLogic()

    fun onSetTextNumeroInternados(): Int {
        return dashboardLogic.getNumeroInternados()
    }

    fun onSetTextConfirmados(): Int {
        return dashboardLogic.getNumeroConfirmados()
    }

    fun onSetTextObitos(): Int {
        return dashboardLogic.getNumeroObitos()
    }

    fun onSetTextRecuperados(): Int {
        return dashboardLogic.getNumeroRecuperados()
    }

    fun onSetTextNovosConfirmados(): Int {
        return dashboardLogic.getNumeroNovosConfirmados()
    }

    fun onSetTextNovosInternados(): Int {
        return dashboardLogic.getNumeroNovosInternados()
    }

    fun onSetTextNovosObitos(): Int {
        return dashboardLogic.getNumeroNovosObitos()
    }

    fun onSetTextNovosRecuperados(): Int {
        return dashboardLogic.getNumeroNovosRecuperados()
    }

    fun onSetTextRNCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisRN()
    }

    fun onSetTextRNCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimaRN()
    }

    fun onSetTextRCCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisRC()
    }

    fun onSetTextRCCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasRC()
    }

    fun onSetTextLVTCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisLVT()
    }

    fun onSetTextLVTCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasLV()
    }

    fun onSetTextAlentejoCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisAlentejo()
    }

    fun onSetTextAlentejoCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasAlentejo()
    }

    fun onSetTextAlgarveCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisAlgarve()
    }

    fun onSetTextAlgarveCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasAlgarve()
    }

    fun onSetTextMadeiraCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisMadeira()
    }

    fun onSetTextMadeiraCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasMadeira()
    }

    fun onSetTextAcoresCasosTotais(): String {
        return dashboardLogic.getNumeroCasosTotaisAcores()
    }

    fun onSetTextAcoresCasosUltima(): String {
        return dashboardLogic.getNumeroCasosUltimasAcores()
    }


}