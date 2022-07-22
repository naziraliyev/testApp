package uz.gita.testapp.presentation.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.domain.AppRepository
import uz.gita.testapp.presentation.viewmodel.DetailsViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), DetailsViewModel {
    override val onclickBackLivedata= MutableLiveData<Unit>()
    override val dataLivaData= MutableLiveData<ImageEntity>()
    override val progressLiveData= MutableLiveData<Boolean>()

    override fun onClickBack() {
        onclickBackLivedata.value = Unit
    }

    override fun initData(id:String) {
        progressLiveData.value = true
            appRepository.getImageDetail(id = id).onEach {
                it.onSuccess { data1->
                    dataLivaData.value = data1
                    delay(500)
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
    }
}