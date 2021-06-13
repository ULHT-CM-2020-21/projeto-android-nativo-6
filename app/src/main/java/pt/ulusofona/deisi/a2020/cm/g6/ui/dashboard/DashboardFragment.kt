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


    fun deafultValuesforUI() {

        numero_internados.text = "0"
        numero_confirmados.text = "0"
        numero_obitos.text = "0"
        numero_recuperados.text = "0"

        numero_novos_confirmados.text = "+0"
        numero_novos_internados.text = "+0"
        numero_novos_obitos.text = "+0"
        numero_novos_recuperados.text = "+0"

        casosTotaisPN.text = "0"
        novosCasosPN.text = "+0"

        casosTotaisC.text = "0"
        novosCasosC.text = "+0"

        casosTotaisLVT.text = "0"
        novosCasosLVT.text = "+0"

        casosTotaisAlentejo.text = "0"
        novosCasosAlentejo.text = "+0"

        casosTotaisAlgarve.text = "0"
        novosCasosAlgarve.text = "+0"

        casosTotaisMadeira.text = "0"
        novosCasosMadeira.text = "+0"

        casosTotaisAcores.text = "0"
        novosCasosAcores.text = "+0"

        dateUpdateText.text = getString(R.string.semDados)
    }


    override fun onUpdateUI(covid: Covid) {
        if (numero_internados != null && covid.internadosTotais != null) {
            numero_internados?.text = String.format("%,d", covid.internadosTotais)
        }
        if (numero_confirmados != null && covid.confirmadosTotais != null) {
            numero_confirmados.text = String.format("%,d", covid.confirmadosTotais)
        }
        if (numero_obitos != null && covid.obitosTotais != null) {
            numero_obitos.text = String.format("%,d", covid.obitosTotais)
        }

        if (numero_recuperados != null && covid.recuperadosTotais != null) {
            numero_recuperados.text = String.format("%,d", covid.recuperadosTotais)
        }

        if (numero_novos_confirmados != null && covid.confirmados24 != null) {
            numero_novos_confirmados.text = String.format("+%,d", covid.confirmados24)
        }

        if (numero_novos_internados != null && covid.internados24 != null) {
            if (covid.internados24 < 0) {
                numero_novos_internados.text = String.format("%,d", covid.internados24)
            } else {
                numero_novos_internados.text = String.format("+%,d", covid.internados24)
            }
        }

        if (numero_novos_obitos != null && covid.obitos24 != null) {
            numero_novos_obitos.text = String.format("+%,d", covid.obitos24)
        }
        if (numero_novos_recuperados != null && covid.recuperados24 != null) {
            numero_novos_recuperados.text = String.format("+%,d", covid.recuperados24)
        }

        if (casosTotaisPN != null && novosCasosPN != null) {
            casosTotaisPN.text = String.format("%,d", covid.norteTotal)
            novosCasosPN.text = String.format("+%,d", covid.norte24)
        }

        if (casosTotaisC != null && novosCasosC != null) {
            casosTotaisC.text = String.format("%,d", covid.centroTotal)
            novosCasosC.text = String.format("+%,d", covid.centro24)

        }

        if (casosTotaisLVT != null && novosCasosLVT != null) {
            casosTotaisLVT.text = String.format("%,d", covid.lisboaTotal)
            novosCasosLVT.text = String.format("+%,d", covid.lisboa24)
        }
        if (casosTotaisAlentejo != null && novosCasosAlentejo != null) {
            casosTotaisAlentejo.text = String.format("%,d", covid.alentejoTotal)
            novosCasosAlentejo.text = String.format("+%,d", covid.alentejo24)
        }
        if (casosTotaisAlgarve != null && novosCasosAlgarve != null) {
            casosTotaisAlgarve.text = String.format("%,d", covid.algarveTotal)
            novosCasosAlgarve.text = String.format("+%,d", covid.alentejo24)
        }
        if (casosTotaisMadeira != null && novosCasosMadeira != null) {
            casosTotaisMadeira.text = String.format("%,d", covid.madeiraTotal)
            novosCasosMadeira.text = String.format("+%,d", covid.madeira24)
        }
        if (casosTotaisAcores != null && novosCasosAcores != null) {
            casosTotaisAcores.text = String.format("%,d", covid.acoresTotal)
            novosCasosAcores.text = String.format("+%,d", covid.acores24)
        }
        if(dateUpdateText != null){
            dateUpdateText.text = covid.data
        }

    }


}