package uz.gita.testapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.testapp.domain.AppRepository
import uz.gita.testapp.domain.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun  bindsRepository(impl: AppRepositoryImpl):AppRepository
}