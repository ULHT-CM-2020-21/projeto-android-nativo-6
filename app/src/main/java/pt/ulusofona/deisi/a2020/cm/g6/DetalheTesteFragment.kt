package pt.ulusofona.deisi.a2020.cm.g6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detalhe_teste.*

class DetalheTesteFragment : Fragment() {
    var teste: TesteCovid = TesteCovid("AHAHAHAHHAHAHAHAHAHAHAHHAHAHAHAHAHA","3/5/2021",false)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detalhe_teste, container, false)
    }

    override fun onStart() {
        super.onStart()
            detalheTesteLocal.text = teste.local
            detalheTesteData.text = teste.data
            detalheTesteResultado.text = teste.resultadoTesteCovid.toString()
    }

}