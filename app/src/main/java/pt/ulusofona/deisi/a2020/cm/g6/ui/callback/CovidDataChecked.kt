package pt.ulusofona.deisi.a2020.cm.g6.ui.callback

interface CovidDataChecked {
    fun checkLastRemoteUpdate(covidCallback: CovidCallback, dashboardCallback: DashboardCallback)
}