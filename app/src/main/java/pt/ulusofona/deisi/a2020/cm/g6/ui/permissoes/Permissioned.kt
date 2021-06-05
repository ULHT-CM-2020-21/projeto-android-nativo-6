package pt.ulusofona.deisi.a2020.cm.g6.ui.permissoes

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission

import androidx.fragment.app.Fragment

abstract class Permissioned(private val requestCode: Int): AppCompatActivity() {
    fun onRequestPermissions(context: Context, permissions: Array<String>){
        var permissionsGive = 0
        permissions.forEach {
            if(checkSelfPermission(context,it) == PackageManager.PERMISSION_GRANTED){
                permissionsGive++
            }else{
                requestPermissions((permissions),requestCode)
                return
            }
        }
        if(permissionsGive == permissions.size){
            onRequestPermissionsSuccess()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if(this.requestCode == requestCode){
            if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                onRequestPermissionsSuccess()
            }else{
                onRequestPermissionsFailure()
            }
        }
    }

    abstract fun onRequestPermissionsSuccess()

    abstract fun onRequestPermissionsFailure()
}