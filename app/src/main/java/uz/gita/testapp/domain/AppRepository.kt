package uz.gita.testapp.domain

import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language
import uz.gita.testapp.data.local.entity.ImageEntity

interface AppRepository {
    fun getImageList(): Flow<Result<List<ImageEntity>>>
    fun getImageDetail(id:String): Flow<Result<ImageEntity>>
    fun setLanguage(language: String)
    fun setMode(state: Boolean)
}