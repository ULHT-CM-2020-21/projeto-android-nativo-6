package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.registo

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_registo.*
import kotlinx.android.synthetic.main.fragment_registo.view.*

import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.R
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.list.TesteSource
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.MainActivity
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils.NavigationManager
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid

import java.util.*


class RegistoFragment : Fragment() {

    private lateinit var viewModel: RegistoViewModel
    val testeSubmete: TesteCovid = TesteCovid()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registo, container, false)
        viewModel = ViewModelProviders.of(this).get(RegistoViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }


    override fun onStart() {
        super.onStart()

        dataTeste.setOnClickListener {
           hideKeyboard(dataTeste)
            showPicker()
        }

        button_camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

    }


    @OnClick(R.id.submeterTeste)
    fun onSubmeterTesteNovoClick(){
        var check = true
        val editLocal = localTeste
        val editData = dataTeste
        val editPositivo = radio_positivo
        val editNegativo = radio_negativo
        val editResultados = escolhaResultados
        val editLocalString: String = editLocal.text.toString()
        val editDataString: String = editData.text.toString()

        if (editLocalString == "" || editLocalString.isEmpty()) {
            editLocal.setError(getString(R.string.localTesteErro))
            check = false
        }

        if (editDataString == "" || editDataString.isEmpty()) {
            editData.setError(getString(R.string.dataTesteErro))
            check = false
        }

        if (!(editPositivo.isChecked()) && !(editNegativo.isChecked())) {
            editResultados.resultadoTesteErro.isVisible = true
            check = false
        }

        if ((editPositivo.isChecked()) || (editNegativo.isChecked())) {
            editResultados.resultadoTesteErro.isVisible = false
        }

        if (check) {
            testeSubmete.local = editLocalString
            testeSubmete.data = editDataString
            TesteSource.addTest(testeSubmete)
            viewModel.onSubmeterTesteNovo(testeSubmete)
            Toast.makeText(
                context as MainActivity,
                getString(R.string.testeSubmetido),
                Toast.LENGTH_SHORT
            ).show()
            activity?.let { NavigationManager.goToListTestes(it.supportFragmentManager) }
        }
    }


    @OnClick(R.id.radio_positivo, R.id.radio_negativo)
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_positivo ->
                    if (checked) {
                        testeSubmete.resultadoTesteCovid = true
                    } else {
                        radio_positivo.setError(getString(R.string.resultadoTesteErro))
                    }

                R.id.radio_negativo ->
                    if (checked) {
                        testeSubmete.resultadoTesteCovid = false
                    } else {
                        radio_negativo.setError(getString(R.string.resultadoTesteErro))
                    }
            }
        }
    }


    fun showPicker() {
        val picker: DatePickerDialog?
        val editDate: EditText? = dataTeste
        val calendar: Calendar = Calendar.getInstance()
        val dia: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mes: Int = calendar.get(Calendar.MONTH)
        val ano: Int = calendar.get(Calendar.YEAR)


        picker = DatePickerDialog(context as MainActivity, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            editDate!!.setText("" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)
        },
            ano,
            mes,
            dia
        )

        picker.show()

    }


   /* fun submeterTeste() {
        var check = true
        val editLocal = localTeste
        val editData = dataTeste
        val editPositivo = radio_positivo
        val editNegativo = radio_negativo
        val editResultados = escolhaResultados
        val editLocalString: String = editLocal.text.toString()
        val editDataString: String = editData.text.toString()

        if (editLocalString == "" || editLocalString.isEmpty()) {
            editLocal.setError(getString(R.string.localTesteErro))
            check = false
        }

        if (editDataString == "" || editDataString.isEmpty()) {
            editData.setError(getString(R.string.dataTesteErro))
            check = false
        }

        if (!(editPositivo.isChecked()) && !(editNegativo.isChecked())) {
            editResultados.resultadoTesteErro.isVisible = true
            check = false
        }

        if ((editPositivo.isChecked()) || (editNegativo.isChecked())) {
            editResultados.resultadoTesteErro.isVisible = false
        }

        if (check) {
            testeSubmete.local = editLocalString
            testeSubmete.data = editDataString
            TesteSource.addTest(testeSubmete)
            Toast.makeText(
                context as MainActivity,
                getString(R.string.testeSubmetido),
                Toast.LENGTH_SHORT
            ).show()
            activity?.let { NavigationManager.goToListTestes(it.supportFragmentManager) }
        }


    }*/

    fun hideKeyboard(view: View){
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}