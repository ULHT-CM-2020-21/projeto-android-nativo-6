package pt.ulusofona.deisi.a2020.cm.g6.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.ui.contactos.ContactosFragment
import pt.ulusofona.deisi.a2020.cm.g6.ui.dashboard.DashboardFragment
import pt.ulusofona.deisi.a2020.cm.g6.ui.detalheTeste.DetalheTesteFragment
import pt.ulusofona.deisi.a2020.cm.g6.ui.graficos.GraficosFragment
import pt.ulusofona.deisi.a2020.cm.g6.ui.listaTestes.ListaTestesFragment
import pt.ulusofona.deisi.a2020.cm.g6.ui.registo.RegistoFragment

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