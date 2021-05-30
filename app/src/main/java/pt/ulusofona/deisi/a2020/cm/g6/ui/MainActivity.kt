package pt.ulusofona.deisi.a2020.cm.g6.ui

import android.app.UiModeManager
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.CovidDatabase
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.OnBatteryPercentageListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.NavigationManager


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnBatteryPercentageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.goToDashboardFragment(supportFragmentManager)
        Battery.start(this)
        Battery.registerListener(this)
    }


    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menuwhite);
        toggle.toolbarNavigationClickListener = View.OnClickListener {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }


    override fun onResume() {
        super.onResume()
        val rnds = (0..1).random()
        if(rnds == 0){
            text_perigo.setText(R.string.NOdanger)
            imagePerigo.setImageResource(R.drawable.green)
        }else{
            text_perigo.setText(R.string.danger)
            imagePerigo.setImageResource(R.drawable.red)
        }

    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_dashboard -> NavigationManager.goToDashboardFragment(supportFragmentManager)
            R.id.nav_Test -> NavigationManager.goToRegisto(supportFragmentManager)
            R.id.list_tests -> NavigationManager.goToListTestes(supportFragmentManager)
            R.id.nav_contactos -> NavigationManager.goToContactos(supportFragmentManager)
            R.id.nav_graph -> NavigationManager.goToGraficos(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPercentageChanged(value: Float) {
        println(value)
        if(value <= 20.0){
            println("tou dark")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            println("tou claro")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}