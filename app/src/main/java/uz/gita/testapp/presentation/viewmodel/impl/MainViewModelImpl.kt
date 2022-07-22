package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.domain.AppRepository
import uz.gita.testapp.presentation.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel(), MainViewModel {

        init {
            loadData()
        }


    override val imageListLiveData =  MutableLiveData<List<ImageEntity>>()
    override val verticalItemListLiveData =  MutableLiveData<List<ImageEntity>>()
    override val progressLiveData =  MutableLiveData<Boolean>()
    override val errorMessageLiveData =  MutableLiveData<String>()
    override val nextScreenLivedata =  MutableLiveData<ImageEntity>()
    override val openSettingScreenLiveData = MutableLiveData<Unit>()

    private fun loadData() {
        appRepository.getImageList().onEach {
            it.onSuccess { list->
                imageListLiveData.value = list
                verticalItemListLiveData.value = list
            }
        }.launchIn(viewModelScope)
    }
    override fun openNextScreen(data: ImageEntity) {
            nextScreenLivedata.value = data
    }

    override fun openSettingScreen() {
        openSettingScreenLiveData.value = Unit
    }

    override fun onRefresh() {
        viewModelScope.launch {
            progressLiveData.value =true
            delay(500)
            loadData()
            progressLiveData.value =false

        }
    }

}