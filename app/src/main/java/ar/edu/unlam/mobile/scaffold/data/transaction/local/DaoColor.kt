package ar.edu.unlam.mobile.scaffold.data.app.local.core.color

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoColor {
    @Insert
    fun insertColor(color: ColorEntity)

    @Update
    fun updateColor(color: ColorEntity)

    @Delete
    fun deleteColor(color: ColorEntity)

    @Query("SELECT * FROM Color")
    fun getAllColors(): List<ColorEntity>

    @Query("SELECT * FROM Color WHERE id = :colorId")
    fun getColorById(colorId: Int): ColorEntity
}
