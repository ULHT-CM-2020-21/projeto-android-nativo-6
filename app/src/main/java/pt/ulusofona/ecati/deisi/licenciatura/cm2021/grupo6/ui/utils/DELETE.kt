package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils

import java.text.SimpleDateFormat
import java.util.*

class DELETE {
    fun Daybetween(date1: String?, date2: String?, pattern: String?): Long {
        val sdf = SimpleDateFormat(pattern, Locale.ENGLISH)
        var Date1: Date? = null
        var Date2: Date? = null
        try {
            Date1 = sdf.parse(date1)
            Date2 = sdf.parse(date2)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return (Date2!!.time - Date1!!.time) / (24 * 60 * 60 * 1000)
    }
}