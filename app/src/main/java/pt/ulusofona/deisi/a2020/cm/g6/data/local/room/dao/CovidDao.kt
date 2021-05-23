package pt.ulusofona.deisi.a2020.cm.g6.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid

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

    @Query("UPDATE covid SET confirmados24= :confirmadosUltimas, recuperados24= :recuperadosUltimas, internados24= :internadosUltimas, obitos24= :obitosUltimas WHERE data= :date")
    fun updateByDate24h(confirmadosUltimas: Int, recuperadosUltimas: Int, internadosUltimas: Int, obitosUltimas: Int, date: String)
}