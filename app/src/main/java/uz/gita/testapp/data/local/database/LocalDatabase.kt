package uz.gita.testapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.testapp.data.local.dao.AppDao
import uz.gita.testapp.data.local.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dao(): AppDao
    companion object {
        lateinit var instance: LocalDatabase

        fun getDatabase(context: Context):LocalDatabase {
            if (::instance.isInitialized) return instance
            instance = Room.databaseBuilder(context, LocalDatabase::class.java, context.packageName).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()

            return instance
        }
    }
}