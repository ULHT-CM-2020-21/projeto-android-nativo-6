package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.dashboard

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


class DashboardFragment : Fragment() , DashboardCallback{

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.askDataCovid(this)

        numero_internados.text = viewModel.onSetTextNumeroInternados()
        numero_confirmados.text =  viewModel.onSetTextConfirmados()
        numero_obitos.text = viewModel.onSetTextObitos()
        numero_recuperados.text =  viewModel.onSetTextRecuperados()

        numero_novos_confirmados.text = viewModel.onSetTextNovosConfirmados()
        numero_novos_internados.text =  viewModel.onSetTextNovosInternados()
        numero_novos_obitos.text =  viewModel.onSetTextNovosObitos()
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

    override fun onUpdateDados() {

        numero_internados.text = viewModel.onSetTextNumeroInternados()
        numero_confirmados.text =  viewModel.onSetTextConfirmados()
        numero_obitos.text = viewModel.onSetTextObitos()
        numero_recuperados.text =  viewModel.onSetTextRecuperados()

        numero_novos_confirmados.text = viewModel.onSetTextNovosConfirmados()
        numero_novos_internados.text =  viewModel.onSetTextNovosInternados()
        numero_novos_obitos.text =  viewModel.onSetTextNovosObitos()
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


}