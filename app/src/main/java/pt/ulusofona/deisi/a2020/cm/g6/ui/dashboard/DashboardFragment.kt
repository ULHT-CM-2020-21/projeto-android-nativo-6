package pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.ui.callback.DashboardCallback


class DashboardFragment : Fragment(), DashboardCallback {

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
        viewModel.askDataCovid(this)
    }

    override fun onUpdateDados(dateUpdate: String) {



        numero_internados.text = String.format("%,d", viewModel.onSetTextNumeroInternados())
        numero_confirmados.text = String.format("%,d", viewModel.onSetTextConfirmados())
        numero_obitos.text = String.format("%,d", viewModel.onSetTextObitos())
        numero_recuperados.text = String.format("%,d", viewModel.onSetTextRecuperados())

        numero_novos_confirmados.text = String.format("%,d", viewModel.onSetTextNovosConfirmados())
        numero_novos_internados.text = String.format("%,d", viewModel.onSetTextNovosInternados())
        numero_novos_obitos.text = String.format("%,d", viewModel.onSetTextNovosObitos())
        numero_novos_recuperados.text = String.format("%,d", viewModel.onSetTextNovosRecuperados())

        casosTotaisPN.text = String.format("%,d", viewModel.onSetTextRNCasosTotais())
        novosCasosPN.text = String.format("%,d", viewModel.onSetTextRNCasosUltima())

        casosTotaisC.text = String.format("%,d", (viewModel.onSetTextRCCasosTotais()))
        novosCasosC.text = String.format("%,d", (viewModel.onSetTextRCCasosUltima()))

        casosTotaisLVT.text = String.format("%,d", (viewModel.onSetTextLVTCasosTotais()))
        novosCasosLVT.text = String.format("%,d", (viewModel.onSetTextLVTCasosUltima()))

        casosTotaisAlentejo.text = String.format("%,d", (viewModel.onSetTextAlentejoCasosTotais()))
        novosCasosAlentejo.text = String.format("%,d", (viewModel.onSetTextAlentejoCasosUltima()))

        casosTotaisAlgarve.text = String.format("%,d", (viewModel.onSetTextAlgarveCasosTotais()))
        novosCasosAlgarve.text = String.format("%,d", (viewModel.onSetTextAlgarveCasosUltima()))

        casosTotaisMadeira.text = String.format("%,d", (viewModel.onSetTextMadeiraCasosTotais()))
        novosCasosMadeira.text = String.format("%,d", (viewModel.onSetTextMadeiraCasosUltima()))

        casosTotaisAcores.text = String.format("%,d", (viewModel.onSetTextAcoresCasosTotais()))
        novosCasosAcores.text = String.format("%,d", (viewModel.onSetTextAcoresCasosUltima()))

        dateUpdateText.text = dateUpdate


    }


}