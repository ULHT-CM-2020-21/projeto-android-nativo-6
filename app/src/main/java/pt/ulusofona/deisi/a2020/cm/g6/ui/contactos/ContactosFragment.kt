package pt.ulusofona.deisi.a2020.cm.g6.ui.contactos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import pt.ulusofona.deisi.a2020.cm.g6.R


class ContactosFragment : Fragment() {

    private lateinit var viewModel: ContactosViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contactos, container, false)
        viewModel = ViewModelProviders.of(this).get(ContactosViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    @OnClick(R.id.button_contactoSNS24)
    fun onClickButtonContactoSNS24(view: View){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+351808242424")
        startActivity(intent)
    }
    @OnClick(R.id.button_contactoSeguSocial)
    fun onClickButtonContactoSeguSocial(view: View){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+351300502502")
        startActivity(intent)
    }
    @OnClick(R.id.button_contactoEmergenciaConsular01)
    fun onClickButtonContactoEmergenciaConsular01(view: View){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+351217929714")
        startActivity(intent)
    }
    @OnClick(R.id.button_contactoEmergenciaConsular02)
    fun onClickButtonContactoEmergenciaConsular02(view: View){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+351961706472")
        startActivity(intent)
    }
    @OnClick(R.id.button_websiteCovid)
    fun onClickButtonWebsiteCovid(view: View){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.min-saude.pt/"))
        startActivity(browserIntent)
    }
    @OnClick(R.id.button_emailCovidDuvidas)
    fun onClickButtonEmailCovidDuvidas(view: View){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:atendimento@SNS24.gov.pt"))
        startActivity(intent);
    }
    @OnClick(R.id.button_websiteEmerConsular)
    fun onClickButtonWebsiteEmerConsular(view: View){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:gec@mne.pt"))
        startActivity(intent);
    }
    @OnClick(R.id.button_LegislacaoCovid)
    fun onClickButtonLegislacaoCovid(view: View){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dre.pt/legislacao-covid-19"))
        startActivity(browserIntent)
    }


}

