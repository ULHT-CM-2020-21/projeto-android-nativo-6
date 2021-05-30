package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_graficos.*
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.DashboardUIListener


class DashboardFragment : Fragment(), DashboardUIListener {

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        deafultValuesforUI()
        viewModel.registerViewListener(this)
        viewModel.askDataCovid()
    }

    override fun onDestroy() {
        super.onDestroy()
        //viewModel.unregisterViewListener(this)
    }

    fun deafultValuesforUI(){
        numero_internados.text = "0"
        numero_confirmados.text = "0"
        numero_obitos.text = "0"
        numero_recuperados.text = "0"

        numero_novos_confirmados.text = "+0"
        numero_novos_internados.text = "+0"
        numero_novos_obitos.text = "+0"
        numero_novos_recuperados.text = "+0"

        casosTotaisPN.text ="0"
        novosCasosPN.text ="+0"

        casosTotaisC.text = "0"
        novosCasosC.text = "+0"

        casosTotaisLVT.text = "0"
        novosCasosLVT.text = "+0"

        casosTotaisAlentejo.text = "0"
        novosCasosAlentejo.text = "+0"

        casosTotaisAlgarve.text = "0"
        novosCasosAlgarve.text ="+0"

        casosTotaisMadeira.text ="0"
        novosCasosMadeira.text = "+0"

        casosTotaisAcores.text = "0"
        novosCasosAcores.text = "+0"

        dateUpdateText.text = getString(R.string.semDados)
    }


    override fun onUpdateUI(covid: Covid) {
        numero_internados.text = String.format("%,d", covid.internadosTotais)
        numero_confirmados.text = String.format("%,d", covid.confirmadosTotais)
        numero_obitos.text = String.format("%,d",covid.obitosTotais)
        numero_recuperados.text = String.format("%,d", covid.recuperadosTotais)

        numero_novos_confirmados.text = String.format("+%,d", covid.confirmados24)
        if(covid.internados24 < 0){
            numero_novos_internados.text = String.format("%,d", covid.internados24)
        }else{
            numero_novos_internados.text = String.format("+%,d", covid.internados24)
        }
        numero_novos_obitos.text = String.format("+%,d", covid.obitos24)
        numero_novos_recuperados.text = String.format("+%,d", covid.recuperados24)

        casosTotaisPN.text = String.format("%,d", covid.norteTotal)
        novosCasosPN.text = String.format("+%,d", covid.norte24)

        casosTotaisC.text = String.format("%,d", covid.centroTotal)
        novosCasosC.text = String.format("+%,d", covid.centro24)

        casosTotaisLVT.text = String.format("%,d", covid.lisboaTotal)
        novosCasosLVT.text = String.format("+%,d", covid.lisboa24)

        casosTotaisAlentejo.text = String.format("%,d", covid.alentejoTotal)
        novosCasosAlentejo.text = String.format("+%,d", covid.alentejo24)

        casosTotaisAlgarve.text = String.format("%,d", covid.algarveTotal)
        novosCasosAlgarve.text = String.format("+%,d", covid.alentejo24)

        casosTotaisMadeira.text = String.format("%,d", covid.madeiraTotal)
        novosCasosMadeira.text = String.format("+%,d", covid.madeira24)

        casosTotaisAcores.text = String.format("%,d", covid.acoresTotal)
        novosCasosAcores.text = String.format("+%,d", covid.acores24)

        dateUpdateText.text = covid.data
    }


}