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
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.R
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.callback.DashboardCallback


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

        numero_internados.text = viewModel.onSetTextNumeroInternados()
        numero_confirmados.text = viewModel.onSetTextConfirmados()
        numero_obitos.text = viewModel.onSetTextObitos()
        numero_recuperados.text = viewModel.onSetTextRecuperados()

        numero_novos_confirmados.text = viewModel.onSetTextNovosConfirmados()
        numero_novos_internados.text = viewModel.onSetTextNovosInternados()
        numero_novos_obitos.text = viewModel.onSetTextNovosObitos()
        numero_novos_recuperados.text = viewModel.onSetTextNovosRecuperados()

        casosTotaisPN.text = viewModel.onSetTextRNCasosTotais()
        novosCasosPN.setText(viewModel.onSetTextRNCasosUltima())

        casosTotaisC.setText(viewModel.onSetTextRCCasosTotais())
        novosCasosC.setText(viewModel.onSetTextRCCasosUltima())

        casosTotaisLVT.setText(viewModel.onSetTextLVTCasosTotais())
        novosCasosLVT.setText(viewModel.onSetTextLVTCasosUltima())

        casosTotaisAlentejo.setText(viewModel.onSetTextAlentejoCasosTotais())
        novosCasosAlentejo.setText(viewModel.onSetTextAlentejoCasosUltima())

        casosTotaisAlgarve.setText(viewModel.onSetTextAlgarveCasosTotais())
        novosCasosAlgarve.setText(viewModel.onSetTextAlgarveCasosUltima())

        casosTotaisMadeira.setText(viewModel.onSetTextMadeiraCasosTotais())
        novosCasosMadeira.setText(viewModel.onSetTextMadeiraCasosUltima())

        casosTotaisAcores.setText(viewModel.onSetTextAcoresCasosTotais())
        novosCasosAcores.setText(viewModel.onSetTextAcoresCasosUltima())

    }

    override fun onUpdateDados(dateUpdate: String) {



        numero_internados.text = String.format("%,d", viewModel.onSetTextNumeroInternados().toDouble().toInt())
        numero_confirmados.text = String.format("%,d", viewModel.onSetTextConfirmados().toDouble().toInt())
        numero_obitos.text = String.format("%,d", viewModel.onSetTextObitos().toDouble().toInt())
        numero_recuperados.text = String.format("%,d", viewModel.onSetTextRecuperados().toDouble().toInt())

        numero_novos_confirmados.text = String.format("%,d", viewModel.onSetTextNovosConfirmados().toDouble().toInt())
        numero_novos_internados.text = String.format("%,d", viewModel.onSetTextNovosInternados().toDouble().toInt())
        numero_novos_obitos.text = String.format("%,d", viewModel.onSetTextNovosObitos().toDouble().toInt())
        numero_novos_recuperados.text = String.format("%,d", viewModel.onSetTextNovosRecuperados().toDouble().toInt())

        casosTotaisPN.text = String.format("%,d", viewModel.onSetTextRNCasosTotais().toDouble().toInt())
        novosCasosPN.text = String.format("%,d", viewModel.onSetTextRNCasosUltima().toDouble().toInt())

        casosTotaisC.text = String.format("%,d", (viewModel.onSetTextRCCasosTotais().toDouble().toInt()))
        novosCasosC.text = String.format("%,d", (viewModel.onSetTextRCCasosUltima().toDouble().toInt()))

        casosTotaisLVT.text = String.format("%,d", (viewModel.onSetTextLVTCasosTotais().toDouble().toInt()))
        novosCasosLVT.text = String.format("%,d", (viewModel.onSetTextLVTCasosUltima().toDouble().toInt()))

        casosTotaisAlentejo.text = String.format("%,d", (viewModel.onSetTextAlentejoCasosTotais().toDouble().toInt()))
        novosCasosAlentejo.text = String.format("%,d", (viewModel.onSetTextAlentejoCasosUltima().toDouble().toInt()))

        casosTotaisAlgarve.text = String.format("%,d", (viewModel.onSetTextAlgarveCasosTotais().toDouble().toInt()))
        novosCasosAlgarve.text = String.format("%,d", (viewModel.onSetTextAlgarveCasosUltima().toDouble().toInt()))

        casosTotaisMadeira.text = String.format("%,d", (viewModel.onSetTextMadeiraCasosTotais().toDouble().toInt()))
        novosCasosMadeira.text = String.format("%,d", (viewModel.onSetTextMadeiraCasosUltima().toDouble().toInt()))

        casosTotaisAcores.text = String.format("%,d", (viewModel.onSetTextAcoresCasosTotais().toDouble().toInt()))
        novosCasosAcores.text = String.format("%,d", (viewModel.onSetTextAcoresCasosUltima().toDouble().toInt()))

        dateUpdateText.text = dateUpdate


    }


}