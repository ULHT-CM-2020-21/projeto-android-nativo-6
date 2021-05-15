package pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.ecati.deisi.licenciatura.cm2021.grupo6.data.local.room.entities.TesteCovid

@Dao
interface TesteCovidDao {

    @Insert
    fun insert(operation: TesteCovid)

    @Query("SELECT * FROM testeCovid")
    fun getAll(): List<TesteCovid>

    @Query("SELECT * FROM testeCovid WHERE uuid= :uuid")
    fun  getById(uuid: String): TesteCovid

    @Query("SELECT * FROM testeCovid WHERE data= :date")
    fun  getByDate(date: String): TesteCovid?
}