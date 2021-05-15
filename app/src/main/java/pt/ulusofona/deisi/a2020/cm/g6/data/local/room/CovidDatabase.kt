package pt.ulusofona.deisi.a2020.cm.g6.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao.CovidDao
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid

@Database(entities = arrayOf(Covid::class), version = 1)
abstract class CovidDatabase: androidx.room.RoomDatabase() {

    abstract fun operationDao(): CovidDao

    companion object{
        private var instance: CovidDatabase? = null

        fun getInstance(applicationContext: Context): CovidDatabase{
            synchronized(this){
                if(instance == null){
                    instance = Room.databaseBuilder(
                        applicationContext,
                        CovidDatabase::class.java,
                        "covid_db"
                    ).build()
                }
                return instance as CovidDatabase
            }
        }


    }
}