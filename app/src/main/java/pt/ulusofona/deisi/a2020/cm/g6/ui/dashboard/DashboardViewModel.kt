package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.CovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.domain.dashboard.DashboardLogic
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.DashboardUIListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchDashboardListener

const val ENDPOINT = "https://covid19-api.vost.pt/Requests/"


class DashboardViewModel(application: Application) : AndroidViewModel(application),
    FetchDashboardListener {

    private val storage = CovidDatabase.getInstance(application).operationDao()
    private val repository = CovidRepository(storage, RetrofitBuilder.getInstance(ENDPOINT))
    private val dashboardLogic = DashboardLogic(repository)
    private val listeners = mutableListOf<DashboardUIListener>()


    fun registerViewListener(listener: DashboardUIListener) {
        listeners.add(listener)
    }

    fun unregisterViewListener(listener: DashboardUIListener) {
        listeners.remove(listener)
    }

    fun notifyViewListeners(covid: Covid) {
        for (i in listeners) {
            i.onUpdateUI(covid)
        }
    }

    fun askDataCovid() {
        dashboardLogic.registerListener(this)
        CoroutineScope(Dispatchers.IO).launch {
            dashboardLogic.askDataToday()
        }
    }

    override fun onDataFetched(covid: Covid) {
        CoroutineScope(Dispatchers.Main).launch {
            notifyViewListeners(covid)
        }
    }


}