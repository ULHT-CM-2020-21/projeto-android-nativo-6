package pt.ulusofona.deisi.a2020.cm.g6.ui



import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.gms.location.LocationResult
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g6.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.permissoes.Permissioned
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.NavigationManager
import java.util.jar.Manifest

const val REQUEST_CODE = 100


class MainActivity : Permissioned(REQUEST_CODE), NavigationView.OnNavigationItemSelectedListener, OnLocationChangedListener {
    override fun onRequestPermissionsSuccess() {
        FusedLocation.start(this)
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailure() {
        println("ERRO Location")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        NavigationManager.goToDashboardFragment(supportFragmentManager)

    }

    override fun onStart() {
        super.onStart()
        super.onRequestPermissions(baseContext, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION))
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

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        println(location.altitude)
        println(location.longitude)
    }


}