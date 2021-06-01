package pt.ulusofona.deisi.a2020.cm.g6.ui.registo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.TesteCovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.TesteCovid
import pt.ulusofona.deisi.a2020.cm.g6.data.repositories.TesteCovidRepositry
import pt.ulusofona.deisi.a2020.cm.g6.domain.registo.RegistoLogic

class RegistoViewModel (application: Application): AndroidViewModel(application){

    var repositryTesteCovid: TesteCovidRepositry = TesteCovidRepositry(TesteCovidDatabase.getInstance(application).testeCovidDao())
    var registoLogic: RegistoLogic = RegistoLogic(repositryTesteCovid)

    fun onSubmeterTesteNovo(testeCovid: TesteCovid){
        CoroutineScope(Dispatchers.IO).launch {
            registoLogic.addTeste(testeCovid)
        }
    }
}