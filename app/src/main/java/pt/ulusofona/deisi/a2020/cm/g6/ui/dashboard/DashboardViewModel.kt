package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.CovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.domain.dashboard.DashboardLogic
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.CovidCallback
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.DashboardCallback

const val ENDPOINT = "https://covid19-api.vost.pt/Requests/"


class DashboardViewModel  (application: Application): AndroidViewModel(application), CovidCallback{

    private val storage = CovidDatabase.getInstance(application).operationDao()
    private val repository =  CovidRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val dashboardLogic = DashboardLogic(repository)

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

    fun onSetTextRNCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisRN()
    }

    fun onSetTextRNCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimaRN()
    }

    fun onSetTextRCCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisRC()
    }

    fun onSetTextRCCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasRC()
    }

    fun onSetTextLVTCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisLVT()
    }

    fun onSetTextLVTCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasLV()
    }

    fun onSetTextAlentejoCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisAlentejo()
    }

    fun onSetTextAlentejoCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasAlentejo()
    }

    fun onSetTextAlgarveCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisAlgarve()
    }

    fun onSetTextAlgarveCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasAlgarve()
    }

    fun onSetTextMadeiraCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisMadeira()
    }

    fun onSetTextMadeiraCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasMadeira()
    }

    fun onSetTextAcoresCasosTotais(): Int {
        return dashboardLogic.getNumeroCasosTotaisAcores()
    }

    fun onSetTextAcoresCasosUltima(): Int {
        return dashboardLogic.getNumeroCasosUltimasAcores()
    }

    fun askDataCovid(dashboardCallback: DashboardCallback) {
        dashboardLogic.askDataToday(this,dashboardCallback)
    }

    override fun getDados(covidData: Covid) {
        dashboardLogic.setDados(covidData)
    }


}