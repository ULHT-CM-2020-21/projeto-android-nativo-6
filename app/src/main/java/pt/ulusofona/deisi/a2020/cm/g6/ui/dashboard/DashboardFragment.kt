package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_dashboard.*
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
        viewModel.registerViewListener(this)
        viewModel.askDataCovid()
    }



    override fun onUpdateUI(covid: Covid) {

        numero_internados.text = String.format("%,d", covid.internadosTotais)
        numero_confirmados.text = String.format("%,d", covid.confirmadosTotais)
        numero_obitos.text = String.format("%,d",covid.obitosTotais)
        numero_recuperados.text = String.format("%,d", covid.recuperadosTotais)

        numero_novos_confirmados.text = String.format("%,d", covid.confirmados24)
        numero_novos_internados.text = String.format("%,d", covid.internados24)
        numero_novos_obitos.text = String.format("%,d", covid.obitos24)
        numero_novos_recuperados.text = String.format("%,d", covid.recuperados24)

        casosTotaisPN.text = String.format("%,d", covid.norteTotal)
        novosCasosPN.text = String.format("%,d", covid.norte24)

        casosTotaisC.text = String.format("%,d", covid.centroTotal)
        novosCasosC.text = String.format("%,d", covid.centro24)

        casosTotaisLVT.text = String.format("%,d", covid.lisboaTotal)
        novosCasosLVT.text = String.format("%,d", covid.lisboa24)

        casosTotaisAlentejo.text = String.format("%,d", covid.alentejoTotal)
        novosCasosAlentejo.text = String.format("%,d", covid.alentejo24)

        casosTotaisAlgarve.text = String.format("%,d", covid.algarveTotal)
        novosCasosAlgarve.text = String.format("%,d", covid.alentejo24)

        casosTotaisMadeira.text = String.format("%,d", covid.madeiraTotal)
        novosCasosMadeira.text = String.format("%,d", covid.madeira24)

        casosTotaisAcores.text = String.format("%,d", covid.acoresTotal)
        novosCasosAcores.text = String.format("%,d", covid.acores24)

        dateUpdateText.text = covid.data
    }


}