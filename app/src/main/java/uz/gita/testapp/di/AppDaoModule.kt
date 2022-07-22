package uz.gita.testapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.testapp.data.local.dao.AppDao
import uz.gita.testapp.data.local.database.LocalDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDaoModule {
    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context):LocalDatabase =LocalDatabase.getDatabase(context)
    @[Provides Singleton]
    fun provideAppDao(localDatabase: LocalDatabase):AppDao =localDatabase.dao()
}