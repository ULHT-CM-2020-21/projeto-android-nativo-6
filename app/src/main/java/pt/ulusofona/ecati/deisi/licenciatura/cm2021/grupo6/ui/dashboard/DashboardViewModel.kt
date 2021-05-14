package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.CovidDatabase
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.remote.RetrofitBuilder
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories.CovidRepository
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.dashboard.DashboardLogic
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.CovidCallback
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.DashboardCallback

const val ENDPOINT = "https://covid19-api.vost.pt/Requests/"


class DashboardViewModel  (application: Application): AndroidViewModel(application), CovidCallback{

    private val storage = CovidDatabase.getInstance(application).operationDao()
    private val repository =  CovidRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val dashboardLogic = DashboardLogic(repository)

    fun onSetTextNumeroInternados(): String {
        return dashboardLogic.getNumeroInternados()
    }

    fun onSetTextConfirmados(): String {
        return dashboardLogic.getNumeroConfirmados()
    }

    fun onSetTextObitos(): String {
        return dashboardLogic.getNumeroObitos()
    }

    fun onSetTextRecuperados(): String {
        return dashboardLogic.getNumeroRecuperados()
    }

    fun onSetTextNovosConfirmados(): String {
        return dashboardLogic.getNumeroNovosConfirmados()
    }

    fun onSetTextNovosInternados(): String {
        return dashboardLogic.getNumeroNovosInternados()
    }

    fun onSetTextNovosObitos(): String {
        return dashboardLogic.getNumeroNovosObitos()
    }

    fun onSetTextNovosRecuperados(): String {
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

    fun askDataCovid(dashboardCallback: DashboardCallback) {
        dashboardLogic.askDataToday(this,dashboardCallback)
    }

    override fun getDados(covidData: Covid) {
        dashboardLogic.setDados(covidData)
    }


}