package uz.gita.testapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData

interface SettingViewModel {
    val onClickBackLiveData: LiveData<Unit>
    val changeModeLiveData: LiveData<Boolean>
    val changeLanguageLivedata: LiveData<String>
    val setModeStateLiveData:LiveData<Boolean>

    fun changeLanguage(language: String)
    fun onClickBack()
    fun setMode(state:Boolean)



}