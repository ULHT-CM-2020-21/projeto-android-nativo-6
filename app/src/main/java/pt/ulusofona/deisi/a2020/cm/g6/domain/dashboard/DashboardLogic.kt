package pt.ulusofona.deisi.a2020.cm.g6.domain.dashboard


import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.CovidRepository
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchDashboardListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchRepositoryCovidListener


class DashboardLogic(private val repository: CovidRepository): FetchRepositoryCovidListener {

    private var covidHoje: Covid? = null
    private var listeners = mutableListOf<FetchDashboardListener>()

    fun askDataToday() {
        repository.getCovidData(this)
    }

    fun registerListener(listener: FetchDashboardListener){
        listeners.add(listener)
    }

    fun unRegisterListener(listener: FetchDashboardListener){
        listeners.remove(listener)
    }

    fun notifyListeners(covid: Covid){
        for(i in listeners){
            i.onDataFetched(covid)
        }
    }

    override fun onFetchedRepository(covid: Covid) {
        notifyListeners(covid)
    }

}