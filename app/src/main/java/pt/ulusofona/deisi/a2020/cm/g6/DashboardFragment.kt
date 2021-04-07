package pt.ulusofona.deisi.a2020.cm.g6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.ArrayList

class DashboardFragment : Fragment() {

    private var regiaoAdapter: RegiaoAdapter? = null
    private var covidHoje:CovidData = CovidData()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onStart() {
        super.onStart()
        var regiaoNorte = Regiao("Norte",String.format("%,d", covidHoje.confirmados_arsnorte),covidHoje.novos_confirmados_arsnorte,"")
        var regiaoCentro = Regiao("Centro",String.format("%,d", covidHoje.confirmados_arscentro),covidHoje.novos_confirmados_arscentro,"")
        var regiaoLVT = Regiao("Lisboa e Vale Do Tejo",String.format("%,d", covidHoje.confirmados_arslvt),covidHoje.novos_confirmados_arsalentejo,"")
        var regiaoAlentejo = Regiao("Alentejo",String.format("%,d", covidHoje.confirmados_arsalentejo),covidHoje.novos_confirmados_arsalentejo,"")
        var regiaoAlgarve = Regiao("Algarve",String.format("%,d", covidHoje.confirmados_arsalgarve),covidHoje.novos_confirmados_arsalgarve,"")
        var regiaoAcores = Regiao("Açores",String.format("%,d", covidHoje.confirmados_acores),covidHoje.novos_confirmados_acores,"")
        var regiaomadeira = Regiao("Madeira",String.format("%,d", covidHoje.confirmados_madeira),covidHoje.novos_confirmados_madeira,"")
        val list = mutableListOf(regiaoNorte,regiaoCentro,regiaoLVT,regiaoAlentejo,regiaoAlgarve,regiaoAcores,regiaomadeira)

        numero_internados.text = String.format("%,d", covidHoje.internados)
        numero_confirmados.text = String.format("%,d", covidHoje.confirmados)
        numero_obitos.text = String.format("%,d", covidHoje.obitos)
        numero_recuperados.text =  String.format("%,d", covidHoje.recuperados)
        numero_novos_confirmados.text = "+ " + covidHoje.confirmados_novos
        numero_novos_internados.text = "+ " + covidHoje.novos_internados
        numero_novos_obitos.text = "+ " + covidHoje.novos_obitos
        numero_novos_recuperados.text = "+ " + covidHoje.novos_recuperados




    }


}