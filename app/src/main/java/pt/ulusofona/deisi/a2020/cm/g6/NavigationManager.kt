package pt.ulusofona.deisi.a2020.cm.g6

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

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


    }
}