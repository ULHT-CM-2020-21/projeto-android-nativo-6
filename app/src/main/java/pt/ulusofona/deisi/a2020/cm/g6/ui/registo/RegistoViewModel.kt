package pt.ulusofona.deisi.a2020.cm.g6.ui.registo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.TesteCovidDatabase
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.repositories.TesteCovidRepositry
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.domain.registo.RegistoLogic

class RegistoViewModel (application: Application): AndroidViewModel(application){

    var repositryTesteCovid: TesteCovidRepositry = TesteCovidRepositry(TesteCovidDatabase.getInstance(application).testeCovidDao())
    var registoLogic: RegistoLogic = RegistoLogic(repositryTesteCovid)

    fun onSubmeterTesteNovo(testeCovid: TesteCovid){
        registoLogic.addTeste(testeCovid)
    }
}