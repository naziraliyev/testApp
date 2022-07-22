package uz.gita.testapp.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.testapp.data.MySharedPref
import uz.gita.testapp.data.local.dao.AppDao
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.data.remote.api.AppApi
import uz.gita.testapp.data.remote.response.toImageEntity
import uz.gita.testapp.utils.isAvailableNetwork
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val appApi: AppApi,
    private val appDao: AppDao,
    private val pref:MySharedPref
) : AppRepository {
    override fun getImageList(): Flow<Result<List<ImageEntity>>> = flow {

        if (context.isAvailableNetwork()) {
            val response = appApi.getPicturesApi()
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    appDao.insertAll(data.map { it.toImageEntity() })
                    emit(Result.success(appDao.getAllList()))
                }
            } else {
                emit(Result.failure(Exception(response.message())))
            }
            return@flow
        } else {
            if (appDao.getAllList().isNullOrEmpty()) {
                emit(Result.success(emptyList()))
                emit(Result.failure(Exception("No Internet Connection")))
                return@flow
            }
            emit(Result.success(appDao.getAllList()))
        }

    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun getImageDetail(id:String): Flow<Result<ImageEntity>> = flow {
        val response = appApi.getPicturesApi("/id/${id}/info")
        Timber.d("data ${response}")

        if (response.isSuccessful){
            response.body()?.let { data->
                Timber.d("data ${data}")
                appDao.insert(data.toImageEntity())
                emit(Result.success(appDao.getDetails(data.id)))
            }
        } else{
            emit(Result.failure(java.lang.Exception(response.message())))
        }
        return@flow
    }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)

    override fun setLanguage(language: String) {
        Timber.d("language$language")
        pref.saveLang(language)
    }

    override fun setMode(state: Boolean) {
        pref.saveMode(state)
    }



}