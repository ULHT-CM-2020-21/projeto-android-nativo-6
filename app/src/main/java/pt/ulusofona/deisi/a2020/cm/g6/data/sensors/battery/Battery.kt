package pt.ulusofona.deisi.a2020.cm.g6.data.sensors.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.ListaUIListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.OnBatteryPercentageListener


class Battery private constructor(private val context: Context): Runnable{

    private val TAG = Battery::class.java.simpleName

    // Intervalo de tempo em que a thread será lançada
    private val TIME_BETWEEN_UPDATES = 20*1000L

    companion object {
        private var listener: OnBatteryPercentageListener? = null
        private var instance: Battery? = null
        private val handler = Handler()

        fun start(context: Context) {
            instance = if(instance == null) Battery(context) else instance
            instance?.start()
        }

        fun registerListener(listener: OnBatteryPercentageListener) {
            this.listener = listener
        }

        fun unregisterListener() {
            listener = null
        }

        fun notifyListener(value: Float) {
            listener?.onPercentageChanged(value)
        }
    }

    private fun start(){
        // Agenda a execução da thread pela primeira vez
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    private fun getBatteryPercentageNow(){
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context.registerReceiver(null, ifilter)
        }

        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        notifyListener(batteryPct!!)
    }

    override fun run() {
        val percentagem = getBatteryPercentageNow()

        // Agenda uma nova thread após a execução
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

}