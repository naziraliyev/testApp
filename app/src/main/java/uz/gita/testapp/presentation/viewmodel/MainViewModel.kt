package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.testapp.data.local.entity.ImageEntity

interface MainViewModel {

    val imageListLiveData:LiveData<List<ImageEntity>>
    val verticalItemListLiveData:LiveData<List<ImageEntity>>
    val progressLiveData:LiveData<Boolean>
    val errorMessageLiveData:LiveData<String>
    val nextScreenLivedata:LiveData<ImageEntity>
    val openSettingScreenLiveData:LiveData<Unit>

    fun openNextScreen(data: ImageEntity)
    fun openSettingScreen()
    fun onRefresh()
}