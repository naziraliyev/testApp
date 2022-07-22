package uz.gita.testapp.presentation.viewmodel.impl

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import uz.gita.testapp.domain.AppRepository
import uz.gita.testapp.presentation.viewmodel.SettingViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingViewModelImpl @Inject constructor(private val appRepository: AppRepository) :
    ViewModel(), SettingViewModel {
    override val onClickBackLiveData = MutableLiveData<Unit>()
    override val changeModeLiveData = MutableLiveData<Boolean>()
    override val changeLanguageLivedata = MutableLiveData<String>()
    override val setModeStateLiveData =  MutableLiveData<Boolean>()

    override fun changeLanguage(language: String) {
        Timber.d("TTT $language")
        changeLanguageLivedata.value = language
        appRepository.setLanguage(language)
    }


    override fun onClickBack() {
        onClickBackLiveData.value = Unit
    }

    override fun setMode(state: Boolean) {
       setModeStateLiveData.value = state
        appRepository.setMode(state)
    }


}