package pt.ulusofona.deisi.a2020.cm.g6.ui.utils

import android.app.Application
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.location.FusedLocation

class LocalizacaoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FusedLocation.start(this)
    }
}