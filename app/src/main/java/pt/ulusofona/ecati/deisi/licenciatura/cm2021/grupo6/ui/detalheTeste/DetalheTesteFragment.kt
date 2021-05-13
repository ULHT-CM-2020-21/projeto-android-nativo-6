package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.detalheTeste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ceylonlabs.imageviewpopup.ImagePopup
import kotlinx.android.synthetic.main.fragment_detalhe_teste.*
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.R
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils.TesteCovid


class DetalheTesteFragment(var teste: TesteCovid) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detalhe_teste, container, false)
    }

    override fun onStart() {
        super.onStart()
        detalheTesteLocal.text = teste.local
        detalheTesteData.text = teste.data
        if (teste.resultadoTesteCovid) {
            detalheTesteResultado.text = getString(R.string.resultadoTestePositivo)
        }else{
            detalheTesteResultado.text = getString(R.string.resultadoTesteNegativo)
        }
        val imagePopup = ImagePopup(context)
        imagePopup.setFullScreen(true)
        imagePopup.setHideCloseIcon(true)
        imagePopup.setImageOnClickClose(true)

        imagePopup.initiatePopup(photo.getDrawable());

        photo.setOnClickListener {
            imagePopup.viewPopup();
        }
        photo_open.setOnClickListener {
            imagePopup.viewPopup();
        }

    }

}