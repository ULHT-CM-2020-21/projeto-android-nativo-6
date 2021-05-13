package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_dashboard.*
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.R


class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()


        /*new Design*/

        numero_internados.text = String.format("%,d", viewModel.onSetTextNumeroInternados())
        numero_confirmados.text = String.format("%,d", viewModel.onSetTextConfirmados())
        numero_obitos.text = String.format("%,d", viewModel.onSetTextObitos())
        numero_recuperados.text = String.format("%,d", viewModel.onSetTextRecuperados())

        numero_novos_confirmados.text = String.format("%,d", viewModel.onSetTextNovosConfirmados())
        numero_novos_internados.text = String.format("%,d", viewModel.onSetTextNovosInternados())
        numero_novos_obitos.text = String.format("%,d", viewModel.onSetTextNovosObitos())
        numero_novos_recuperados.text = String.format("%,d", viewModel.onSetTextNovosRecuperados())

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