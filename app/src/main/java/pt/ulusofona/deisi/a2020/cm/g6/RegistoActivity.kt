package pt.ulusofona.deisi.a2020.cm.g6

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_registo.*
import kotlinx.android.synthetic.main.activity_registo.view.*
import java.util.*


class RegistoActivity : AppCompatActivity() {
    var editData: EditText? = dataTeste

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)
    }

    override fun onStart() {
        super.onStart()

        submeterTeste.setOnClickListener {
            submeterTeste()
        }

        dataTeste.setOnClickListener {
            /*showPicker()*/
        }

        button_camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_positivo ->
                    if (!checked) {
                        radio_positivo.setError(getString(R.string.resultadoTesteErro))
                    }

                R.id.radio_negativo ->
                    if (!checked) {
                        radio_negativo.setError(getString(R.string.resultadoTesteErro))
                    }
            }
        }
    }


    /*fun showPicker() {
        val picker: DatePickerDialog? = null
        val calendar: Calendar = Calendar.getInstance()
        val dia: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val mes: Int = calendar.get(Calendar.MONTH)
        val ano: Int = calendar.get(Calendar.YEAR)


        picker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)

            },
            year,
            month,
            day
        )

        dpd.show()

    }*/


    fun submeterTeste() {
        val editLocal = localTeste
        val editData = dataTeste
        val editPositivo = radio_positivo
        val editNegativo = radio_negativo
        val editResultados = escolhaResultados
        val editLocalString: String = editLocal.text.toString()
        val editDataString: String = editData.text.toString()

        if (editLocalString == "") {
            editLocal.setError(getString(R.string.localTesteErro))
        }

        if (editDataString == "") {
            editData.setError(getString(R.string.dataTesteErro))
        }

        if (!(editPositivo.isChecked()) && !(editNegativo.isChecked())) {
            editResultados.resultadoTesteErro.isVisible = true
        }


    }
}