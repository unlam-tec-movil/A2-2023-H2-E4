package ar.edu.unlam.mobile.scaffold.data.app.local.core.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "transaction_type_id")
    val transaction_type_id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "color_id")
    val color_id: Int,
)
