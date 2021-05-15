package pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.Covid

@Dao
interface CovidDao {

    @Insert
    fun insert(operation: Covid)

    @Query("SELECT * FROM covid")
    fun getAll(): List<Covid>

    @Query("SELECT * FROM covid WHERE uuid= :uuid")
    fun  getById(uuid: String): Covid

    @Query("SELECT * FROM covid WHERE data= :date")
    fun  getByDate(date: String): Covid?

    //#TODO APAGAR DEBUG
    @Query("DELETE FROM covid")
    fun  deleteAllWARNING()
}