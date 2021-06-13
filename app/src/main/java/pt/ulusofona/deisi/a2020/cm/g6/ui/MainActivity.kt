package pt.ulusofona.deisi.a2020.cm.g6.ui

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.google.android.gms.location.LocationResult
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.remote.LocalizacaoRemote
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.battery.Battery
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.FetchDanger
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.OnBatteryPercentageListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.permissoes.Permissioned
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.NavigationManager
import java.util.*


const val REQUEST_CODE = 100


class MainActivity : Permissioned(REQUEST_CODE), NavigationView.OnNavigationItemSelectedListener, OnBatteryPercentageListener, OnLocationChangedListener, FetchDanger {
    override fun onRequestPermissionsSuccess() {
        FusedLocation.start(this)
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailure() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.goToDashboardFragment(supportFragmentManager)
        Battery.start(this)
        Battery.registerListener(this)
    }

    override fun onStart() {
        super.onStart()
        super.onRequestPermissions(
            baseContext, arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        LocalizacaoRemote.registerListener(this)
        text_perigo.setText("N/A")
        imagePerigo.setImageResource(R.drawable.error)
    }


    private fun setupDrawerMenu() {
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


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
        if (value <= 20.0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(this, Locale.getDefault())
        addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses[0].getAdminArea() != null) {
            val state: String = addresses[0].getAdminArea()
            LocalizacaoRemote.getLocationDanger(state)
        }

    }

    override fun onfecthDanger(resultado: String) {
        when (resultado.toLowerCase()) {
            "muito baixa" -> text_perigo.setText(R.string.muitobaixa)
            "baixa" -> text_perigo.setText(R.string.baixa)

            "baixo a moderado" -> text_perigo.setText(R.string.baixoModerado)
            "moderado" -> text_perigo.setText(R.string.moderado)

            "elevada" -> text_perigo.setText(R.string.elevada)
            "muito elevada" -> text_perigo.setText(R.string.muitoElevada)
            "extremamente elevada" -> text_perigo.setText(R.string.extraElevada)
            else -> text_perigo.setText("N/A")
        }

        when (resultado.toLowerCase()) {
            "muito baixa" -> imagePerigo.setImageResource(R.drawable.green)
            "baixa" -> imagePerigo.setImageResource(R.drawable.green)

            "baixo a moderado" -> imagePerigo.setImageResource(R.drawable.yellow)
            "moderado" -> imagePerigo.setImageResource(R.drawable.orange)

            "elevada" -> imagePerigo.setImageResource(R.drawable.red)
            "muito elevada" -> imagePerigo.setImageResource(R.drawable.red)
            "extremamente elevada" -> imagePerigo.setImageResource(R.drawable.red)
            else -> imagePerigo.setImageResource(R.drawable.error)
        }
    }


}