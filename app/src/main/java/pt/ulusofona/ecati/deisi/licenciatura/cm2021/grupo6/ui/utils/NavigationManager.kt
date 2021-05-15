package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.R
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.contactos.ContactosFragment
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.dashboard.DashboardFragment
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.detalheTeste.DetalheTesteFragment
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.graficos.GraficosFragment
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.listaTestes.ListaTestesFragment
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.ui.registo.RegistoFragment


abstract class NavigationManager {

    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToDashboardFragment(fm: FragmentManager){
            placeFragment(fm, DashboardFragment())
        }

        fun goToRegisto(fm: FragmentManager){
            placeFragment(fm, RegistoFragment())
        }

        fun goToListTestes(fm: FragmentManager){
            placeFragment(fm, ListaTestesFragment())
        }

        fun goToTest(fm: FragmentManager,test: TesteCovid){
            placeFragment(fm, DetalheTesteFragment(test))
        }

        fun goToContactos(fm: FragmentManager){
            placeFragment(fm, ContactosFragment())
        }

        fun goToGraficos(fm: FragmentManager){
            placeFragment(fm, GraficosFragment())
        }




    }
}