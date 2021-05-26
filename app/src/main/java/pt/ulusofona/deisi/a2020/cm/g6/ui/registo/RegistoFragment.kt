package pt.ulusofona.deisi.a2020.cm.g6.ui.registo

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_registo.*
import kotlinx.android.synthetic.main.fragment_registo.view.*
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.ui.MainActivity
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.NavigationManager
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws


class RegistoFragment : Fragment() {

    private lateinit var viewModel: RegistoViewModel
    val testeSubmete: TesteCovid = TesteCovid()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            dispatchTakePictureIntent()
        }

    }

    var currentPhotoPath: String? = null

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1
     var photoURIFinal: Uri? = null
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "pt.ulusofona.deisi.a2020.cm.g6",
                        it
                    )
                    photoURIFinal = photoURI
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
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
            if(photoURIFinal != null){
                testeSubmete.fotoPath = photoURIFinal.toString()
            }
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


        picker = DatePickerDialog(
            context as MainActivity,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                editDate!!.setText("" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year)
            },
            ano,
            mes,
            dia
        )

        picker.show()

    }


    fun hideKeyboard(view: View){
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}