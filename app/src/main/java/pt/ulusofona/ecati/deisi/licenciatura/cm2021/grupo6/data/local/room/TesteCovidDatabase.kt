package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao.TesteCovidDao
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid

@Database(entities = arrayOf(TesteCovid::class), version = 1)
abstract class TesteCovidDatabase : androidx.room.RoomDatabase() {

    abstract fun testeCovidDao(): TesteCovidDao

    companion object{
        private var instance: TesteCovidDatabase? = null

        fun getInstance(applicationContext: Context): TesteCovidDatabase{
            synchronized(this){
                if(instance == null){
                    instance = Room.databaseBuilder(
                        applicationContext,
                        TesteCovidDatabase::class.java,
                        "testeCovid_db"
                    ).build()
                }
                return instance as TesteCovidDatabase
            }
        }

    }
}