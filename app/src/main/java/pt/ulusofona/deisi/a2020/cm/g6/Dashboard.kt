package pt.ulusofona.deisi.a2020.cm.g6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

    }

    override fun onStart() {
        super.onStart()


        numero_internados.text = String.format("%,d", CovidData.internados)
        numero_confirmados.text = String.format("%,d", CovidData.confirmados)
        numero_obitos.text = String.format("%,d", CovidData.obitos)
        numero_recuperados.text =  String.format("%,d", CovidData.recuperados)
        numero_novos_confirmados.text = "+ " + CovidData.confirmados_novos
        numero_novos_internados.text = "+ " + CovidData.novos_internados
        numero_novos_obitos.text = "+ " + CovidData.novos_obitos
        numero_novos_recuperados.text = "+ " + CovidData.novos_recuperados
    }
}
