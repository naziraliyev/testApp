package uz.gita.testapp.data.local.dao

import androidx.room.*
import uz.gita.testapp.data.local.entity.ImageEntity

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<ImageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data:ImageEntity)

    @Update
    fun update(data:ImageEntity)

    @Delete
    fun delete(data: ImageEntity)

    @Query("select * from ImageEntity")
    fun getAllList():List<ImageEntity>

    @Query("select * from ImageEntity where id==:id")
    fun getDetails(id :String):ImageEntity

}