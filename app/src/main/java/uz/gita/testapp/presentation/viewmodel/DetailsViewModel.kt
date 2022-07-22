package uz.gita.testapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.testapp.data.local.entity.ImageEntity


interface DetailsViewModel {

    val onclickBackLivedata:LiveData<Unit>
    val dataLivaData:LiveData<ImageEntity>
    val progressLiveData:LiveData<Boolean>

    fun onClickBack()
    fun initData(id:String)

}