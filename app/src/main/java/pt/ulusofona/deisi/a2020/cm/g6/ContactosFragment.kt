package pt.ulusofona.deisi.a2020.cm.g6

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_contactos.*


class ContactosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contactos, container, false)
    }

    override fun onStart() {
        super.onStart()
        button_contactoSNS24.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+351808242424")
            startActivity(intent)
        }
        button_contactoSeguSocial.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+351300502502")
            startActivity(intent)
        }
        button_contactoEmergenciaConsular01.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+351217929714")
            startActivity(intent)
        }
        button_contactoEmergenciaConsular02.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+351961706472")
            startActivity(intent)
        }
        button_websiteCovid.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.min-saude.pt/"))
            startActivity(browserIntent)
        }
        button_emailCovidDuvidas.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.parse("mailto:atendimento@SNS24.gov.pt"))
            startActivity(intent);
        }

        button_websiteEmerConsular.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.setData(Uri.parse("mailto:gec@mne.pt"))
            startActivity(intent);
        }
        button_LegislacaoCovid.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://dre.pt/legislacao-covid-19"))
            startActivity(browserIntent)
        }





    }


}